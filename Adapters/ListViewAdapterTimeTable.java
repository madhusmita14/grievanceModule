package com.madhusmita.final__ois.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.madhusmita.final__ois.EntityClasses.ModelTimeTable;
import com.madhusmita.final__ois.R;

import java.util.List;

public class ListViewAdapterTimeTable extends ArrayAdapter<ModelTimeTable> {

    private List<ModelTimeTable> itemData;
    private Context context;

    public ListViewAdapterTimeTable(List<ModelTimeTable> itemData, Context context) {
        super(context, R.layout.contentrow,itemData);
        this.itemData = itemData;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.contentrow, null, true);

        TextView tv_subject = listViewItem.findViewById(R.id.subject);
        TextView tv_teacher = listViewItem.findViewById(R.id.teacher_name);
        TextView tv_room = listViewItem.findViewById(R.id.room);
        TextView tv_timing = listViewItem.findViewById(R.id.timing);

        ModelTimeTable modelTimeTable = itemData.get(position);
        tv_subject.setText(modelTimeTable.getSubject());
        tv_teacher.setText(modelTimeTable.getTeacher());
        tv_room.setText(modelTimeTable.getRoom());
        tv_timing.setText(modelTimeTable.getTiming());

        return listViewItem;


    }
}
