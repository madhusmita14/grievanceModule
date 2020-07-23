package com.madhusmita.final__ois.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.madhusmita.final__ois.EntityClasses.GrievenceModel;
import com.madhusmita.final__ois.R;

import java.util.List;


public class ListViewAdapterGrievence extends ArrayAdapter<GrievenceModel> {

    private List<GrievenceModel> itemData;
    private Context context;

    public ListViewAdapterGrievence(List<GrievenceModel> itemData, Context context) {
        super(context, R.layout.contentrow, itemData);
        this.itemData = itemData;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.contentrowgrievence, null, true);

        TextView tv_id = listViewItem.findViewById(R.id.id);
        final ImageView popmenu=listViewItem.findViewById(R.id.menu);

        GrievenceModel model = itemData.get(position);
        tv_id.setText(model.getId());
        popmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,popmenu);
                popupMenu.inflate(R.menu.card_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.close:
                                Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.reopen:
                                Toast.makeText(context,"Reopen",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return listViewItem;


    }
}
