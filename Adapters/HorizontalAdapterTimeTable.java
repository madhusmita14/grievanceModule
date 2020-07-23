package com.madhusmita.final__ois.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.madhusmita.final__ois.EntityClasses.ButtonModel;
import com.madhusmita.final__ois.FriFragment;
import com.madhusmita.final__ois.MonFragment;
import com.madhusmita.final__ois.R;
import com.madhusmita.final__ois.SatFragment;
import com.madhusmita.final__ois.ThursFragment;
import com.madhusmita.final__ois.TimeTable;
import com.madhusmita.final__ois.TueFragment;
import com.madhusmita.final__ois.WedFragment;

import java.util.List;


public class HorizontalAdapterTimeTable extends RecyclerView.Adapter<HorizontalAdapterTimeTable.HorizontalViewHolderJson> {


    private List<ButtonModel> itemDay;
    private Context context;
    private TimeTable ma;
    private FragmentActivity fragmentActivity;

    public HorizontalAdapterTimeTable(List<ButtonModel> itemDay, Context context, TimeTable ma) {
        this.itemDay = itemDay;
        this.context = context;
        this.ma=ma;
    }

    @NonNull
    @Override
    public HorizontalViewHolderJson onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dayrow2,parent,false);
        return new HorizontalViewHolderJson(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolderJson holder, int position) {
        holder.dayBtn.setText(itemDay.get(position).getDayName());
    }

    @Override
    public int getItemCount() {
        return itemDay.size();
    }

    public class HorizontalViewHolderJson extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button dayBtn;

        public HorizontalViewHolderJson(@NonNull View itemView) {
            super(itemView);
            dayBtn = itemView.findViewById(R.id.dayBtn);
            dayBtn.setClickable(true);
            dayBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(itemView.getContext(), "Clicked Card..." + getAdapterPosition(), Toast.LENGTH_SHORT).show();

            if (getAdapterPosition()==0)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new MonFragment()).commit();
            }
            else if(getAdapterPosition()==1)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new TueFragment()).commit();
            }
            else if(getAdapterPosition()==2)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new WedFragment()).commit();
            }
            else if(getAdapterPosition()==3)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new ThursFragment()).commit();
            }
            else if(getAdapterPosition()==4)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new FriFragment()).commit();
            }
            else if(getAdapterPosition()==5)
            {
                ma.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentid,new SatFragment()).commit();
            }
        }
    }

}
