package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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
        activityModelArrayList.add(new ActivityModel("Dinner", R.drawable.ic_gfglogo));
        activityModelArrayList.add(new ActivityModel("International", R.drawable.ic_gfglogo));
        activityModelArrayList.add(new ActivityModel("Working", R.drawable.ic_gfglogo));
           if (selected.contains("business")) {
            activityModelArrayList.add(new ActivityModel("Formal", R.drawable.ic_gfglogo));
        }
           if (selected.contains("leisure")) {
               activityModelArrayList.add(new ActivityModel("Gym", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Swimming", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Running", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Walking", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Baby", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Snow", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Beach", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Photography", R.drawable.ic_gfglogo));
               activityModelArrayList.add(new ActivityModel("Hiking", R.drawable.ic_gfglogo));
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
                    view.setBackgroundColor(Color.BLUE);
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
                if (!activityList.isEmpty()) {
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