package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class commonContent extends AppCompatActivity {
    GridView activityGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_content);

        activityGV = findViewById(R.id.idGVactivity);
        ArrayList<ActivityModel> activityModelArrayList = new ArrayList<ActivityModel>();
        Bundle extra = getIntent().getExtras();
        String destination = extra.getString("Destination");
        String date = extra.getString("Date");
        ArrayList<String> selected = extra.getStringArrayList("selected");
        Button finalbutton = (Button) findViewById(R.id.lastbutton);

        ArrayList<String> cyclingList = new ArrayList<String>(Arrays.asList("Helmet", "Water bottle", "Cyclng suit"));
        activityModelArrayList.add(new ActivityModel("食事", R.drawable.img_1));
        activityModelArrayList.add(new ActivityModel("外国", R.drawable.img_2));
        activityModelArrayList.add(new ActivityModel("仕事", R.drawable.img_3));
           if (selected.contains("business")) {
            activityModelArrayList.add(new ActivityModel("ブシネス", R.drawable.img_4));
        }
           if (selected.contains("leisure")) {
               activityModelArrayList.add(new ActivityModel("ジム", R.drawable.img_5));
               activityModelArrayList.add(new ActivityModel("水泳", R.drawable.img_6));
               activityModelArrayList.add(new ActivityModel("ランニング", R.drawable.img_7));
               activityModelArrayList.add(new ActivityModel("ウォーキング", R.drawable.img_8));
               activityModelArrayList.add(new ActivityModel("Baby", R.drawable.img_12));
               activityModelArrayList.add(new ActivityModel("雪", R.drawable.img_9));
               activityModelArrayList.add(new ActivityModel("ビーチ", R.drawable.img_10));
               activityModelArrayList.add(new ActivityModel("写真撮影", R.drawable.img_11));
               activityModelArrayList.add(new ActivityModel("ハイキング", R.drawable.img_14));
           }

        ActivityGVAdapter adapter = new ActivityGVAdapter(this, activityModelArrayList);
        activityGV.setAdapter(adapter);
        ArrayList<String> activityList = new ArrayList<>();
        activityGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ActivityModel activityModel = (ActivityModel) parent.getItemAtPosition(position);
                view = activityGV.getChildAt(position);
                if (!activityModel.isChecked()){
                    activityModel.isChecked(true);
                    activityList.add(activityModel.getActivity_name());
                    view.setBackgroundColor(Color.parseColor("#FFC0CB"));
                }
                else{
                    activityModel.isChecked(false);
                    activityList.remove(activityModel.getActivity_name());
                    view.setBackgroundColor(Color.WHITE);
                }
                System.out.println(activityList);
            }
        });
        finalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), carryList.class);
                intent.putExtra("LIST_ITEMS", activityList);
                intent.putExtra("Destination", destination);
                intent.putExtra("Date", date);
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                User user = new User(destination, date, activityList);
                if (!activityList.isEmpty()) {
                    finish();
                    FirebaseDatabase.getInstance().getReference("Trips")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                            .setValue(user).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Trip created", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Unable to create trip", Toast.LENGTH_LONG).show();
                                }
                            });
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Select atleast one activity",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}