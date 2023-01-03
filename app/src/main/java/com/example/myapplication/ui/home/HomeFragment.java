package com.example.myapplication.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.User;
import com.example.myapplication.carryList;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Trips").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ArrayList<String> tutorials = new ArrayList<String>();
        ArrayList<String> tripDate = new ArrayList<String>();
        ArrayList<String> activities = new ArrayList<String>();
        ArrayList<String> refs = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    tutorials.add(datas.child("destination").getValue().toString());
                    tripDate.add(datas.child("tripDate").getValue().toString());
                    activities.add(datas.child("activityList").getValue().toString());
                    refs.add(String.valueOf((datas.getRef())));

                    HomeAdapter adapter = new HomeAdapter(getContext(), tutorials);
                    binding.listview.setAdapter(adapter);

                    binding.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setMessage("Are you sure you want to delete?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Query query = reference.orderByChild("destination").equalTo(tutorials.get(i));
                                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                                        snapshot.getRef().removeValue();
                                                        Toast.makeText(getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                                                        tutorials.remove(i);
                                                        adapter.notifyDataSetChanged();
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

                            return false;
                        }
                    });

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), carryList.class);
                intent.putExtra("Destination", tutorials.get(i));
                intent.putExtra("Date", tripDate.get(i));
                ArrayList<String> list = new ArrayList<>(Arrays.asList(activities.get(i).split(",")));
                intent.putExtra("LIST_ITEMS", list);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}