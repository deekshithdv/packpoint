package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LolModel {

//    package com.example.myapplication;
//
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//    public class carryList extends AppCompatActivity {
//        String[] working = {"abc", "xyz", "da", "afas"};
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_carry_list);
//            Bundle bundle = getIntent().getExtras();
//            ArrayList<String> myListData2 = bundle.getStringArrayList("LIST_ITEMS");
//            System.out.println("######");
//            System.out.println(myListData2);
//            System.out.println("######");
////        List<MyListData> myListData = new ArrayList<MyListData>();
////        myListData = myListData2;
////        for (int i = 0; i < working.length; i++){
////            myListData.add(new MyListData(working[i]));
////        }
////        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
////        MyListAdapter adapter = new MyListAdapter(myListData);
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setAdapter(adapter);
//
//            ListView list = (ListView) findViewById(R.id.itemlist);
//            ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myListData2);
//            System.out.println("######");
//            System.out.println(items);
//            System.out.println("######");
//            list.setAdapter(items);
//        }
//    }
}
