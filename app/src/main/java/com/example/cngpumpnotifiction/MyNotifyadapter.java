package com.example.cngpumpnotifiction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyNotifyadapter extends RecyclerView.Adapter<MyNotifyadapter.Myviewholder>{
    List<pumplist>list;
    Context context ;
    Dashboard.OnPumpAddListener addListener;

    public MyNotifyadapter(List<pumplist> list, Context context) {
        this.list = list;
        this.context = context;
        this.addListener = addListener;
    }

    @NonNull
    @Override
    public MyNotifyadapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pumplist_customer,parent,false);
        MyNotifyadapter.Myviewholder holder = new MyNotifyadapter.Myviewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNotifyadapter.Myviewholder holder, int position) {
        holder.pumpname.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.Lan_loc.setText(list.get(position).getLocation());
        holder.time.setText(list.get(position).getTime());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dashboard activity = (Dashboard) context;
                FragmentTransaction transaction =activity.getSupportFragmentManager().beginTransaction();
                Fragment fragment = new notify_message();
                Bundle bundle = new Bundle();
                bundle.putString("id",list.get(position).getId());
                fragment.setArguments(bundle);
                transaction.add(R.id.petrolfrem,fragment);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView pumpname,address,Lan_loc,time;
        RelativeLayout layout;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            pumpname = itemView.findViewById(R.id.pumpname);
            address = itemView.findViewById(R.id.pumpadd);
            Lan_loc = itemView.findViewById(R.id.LANloca);
            time = itemView.findViewById(R.id.openhours);
            layout = itemView.findViewById(R.id.layout1);

        }
    }
}
