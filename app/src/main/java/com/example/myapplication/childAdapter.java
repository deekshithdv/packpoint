package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class childAdapter extends BaseAdapter {
    ArrayList<Object> list;
    private static final int childCount = 0;
    private static final int headercount = 1;
    private LayoutInflater inflater;

    public childAdapter(Context context, ArrayList<Object> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof childModel ){
            return childCount;
        }
        else {
            return  headercount;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            switch (getItemViewType(i)){
                case childCount:
                    view = inflater.inflate(R.layout.child, null);
                    break;
                case headercount:
                    view = inflater.inflate(R.layout.header, null);
                    break;
            }
        }
        switch (getItemViewType(i)){
            case childCount:
                TextView textView = (TextView) view.findViewById(R.id.childName);
                textView.setText(((childModel)list.get(i)).getItemname());
                break;
            case headercount:
                TextView textView1 = (TextView) view.findViewById(R.id.header_title);
                textView1.setText((String)list.get(i));
                break;
        }
        return view;
    }
}
