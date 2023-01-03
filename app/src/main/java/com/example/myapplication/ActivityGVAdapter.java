package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ActivityGVAdapter extends ArrayAdapter<ActivityModel> {

    public ActivityGVAdapter(@NonNull Context context, ArrayList<ActivityModel> activityModelArrayList) {
        super(context, 0, activityModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        ActivityGVAdapter.ViewHolder holder = null;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) listitemView.findViewById(R.id.idTVActivity);
            holder.image = (ImageView) listitemView.findViewById(R.id.idIVActivity);
            listitemView.setTag(holder);
        } else {
            holder = (ActivityGVAdapter.ViewHolder) listitemView.getTag();
        }
        ActivityModel activityModel = getItem(position);
        holder.imageTitle.setText(activityModel.getActivity_name());
        holder.image.setImageResource(activityModel.getImgid());
        return listitemView;
    }
    public static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
