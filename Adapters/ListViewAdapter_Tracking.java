package com.madhusmita.final__ois.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.madhusmita.final__ois.EntityClasses.TrackingModel;
import com.madhusmita.final__ois.R;

import java.util.List;

public class ListViewAdapter_Tracking extends ArrayAdapter<TrackingModel> {

    private List<TrackingModel> heroList;
    private Context mCtx;


    public ListViewAdapter_Tracking(List<TrackingModel> heroList, Context mCtx) {
        super(mCtx, R.layout.tracking_status_list_item, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View listViewItem = inflater.inflate(R.layout.tracking_status_list_item, null, true);

        TextView textViewStatus = listViewItem.findViewById(R.id.textViewStatus);
        TextView textViewTime = listViewItem.findViewById(R.id.textViewTime);

        TrackingModel hero = heroList.get(position);

        textViewStatus.setText(hero.getStatus());
        textViewTime.setText(hero.getTime());

         if(hero.getStatus().equalsIgnoreCase("Grievence Filed"))
         {
             textViewStatus.setTextColor(Color.RED);
         }
         else if (hero.getStatus().equalsIgnoreCase("Grievence Completed")){
             textViewStatus.setTextColor(Color.GREEN);
         }
         else
         {
             textViewStatus.setTextColor(Color.BLUE);
         }

        return listViewItem;
    }
}
