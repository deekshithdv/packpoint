package com.example.myapplication;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class carryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carry_list);
        String[] International = {"Passport", "Visa", "Itinerary", "Ticket"};
        String[] Formal = {"Formal shirt", "Formal pant", "Formal shoes", "Tie", "Belt" };
        String[] Working = {"Laptop", "Laptop Charger", "Storage devices"};
        String[] Dinner = {"Nice shirt", "Nice jacket", "Shoes", "Shoe polish"};
        String[] Swimming = {"Swimming suit", "Spectacles"};
        String[] Running = {"Running shoes", "Socks"};
        String[] Gym = {"Nice shirt", "Nice jacket", "Shoes", "Shoe polish"};
        String[] Walking = {"Swimming suit", "Spectacles"};
        String[] Baby = {"Running shoes", "Socks"};
        String[] Snow = {"Nice shirt", "Nice jacket", "Shoes", "Shoe polish"};
        String[] Beach = {"Swimming suit", "Spectacles"};
        String[] Photography = {"Running shoes", "Socks"};
        String[] Hiking = {"Running shoes", "Socks"};

        ListView listView = (ListView) findViewById(R.id.itemlist);
        Bundle extra = getIntent().getExtras();
        ArrayList<String> activities = extra.getStringArrayList("LIST_ITEMS");
        TextView destinationtv = (TextView)findViewById(R.id.destination);
        TextView datetv = (TextView) findViewById(R.id.date);
        String destination = extra.getString("Destination");
        String date = extra.getString("Date");
        destinationtv.setText(destination);
        datetv.setText(date);

        ArrayList<Object> list = new ArrayList<>();
        for(int i=0; i<activities.size(); i++){
            switch (activities.get(i)){
                case "International":
                    list.add(new String("International"));
                    list = funcAddList(list, International);
                    break;
                case "Working":
                    list.add(new String("Working"));
                    list = funcAddList(list, Working);
                    break;
                case "Dinner":
                    list.add(new String("Dinner"));
                    list = funcAddList(list, Dinner);
                    break;
                case "Formal":
                    list.add(new String("Formal"));
                    list = funcAddList(list, Formal);
                    break;
                case "Walking":
                    list.add(new String("Walking"));
                    list = funcAddList(list, Walking);
                    break;
                case "Swimming":
                    list.add(new String("Swimming"));
                    list = funcAddList(list, Swimming);
                    break;
                case "Beach":
                    list.add(new String("Beach"));
                    list = funcAddList(list, Beach);
                    break;
                case "Running":
                    list.add(new String("Running"));
                    list = funcAddList(list, Running);
                    break;
                case "Baby":
                    list.add(new String("Baby"));
                    list = funcAddList(list, Baby);
                    break;
                case "Snow":
                    list.add(new String("Snow"));
                    list = funcAddList(list, Snow);
                    break;
                case "Gym":
                    list.add(new String("Gym"));
                    list = funcAddList(list, Gym);
                    break;
                case "Photography":
                    list.add(new String("Photography"));
                    list = funcAddList(list, International);
                    break;
                case "Hiking":
                    list.add(new String("Hiking"));
                    list = funcAddList(list, Hiking);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + activities.get(i));
            }
        }


        listView.setAdapter(new childAdapter(this, list));
    }
    public ArrayList<Object> funcAddList(ArrayList<Object> arraylist, String[] list){
        for(int i=0; i< list.length; i++) {
            arraylist.add(new childModel(list[i]));
        }
        return arraylist;

    }
}