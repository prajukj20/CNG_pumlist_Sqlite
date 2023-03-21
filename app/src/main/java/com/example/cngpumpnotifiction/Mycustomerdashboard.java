package com.example.cngpumpnotifiction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Mycustomerdashboard  extends RecyclerView.Adapter<Mycustomerdashboard.Myviewholder>  {
    List<pumplist> list;
    Context context ;
    Dashboard.OnPumpAddListener addListener;

    public Mycustomerdashboard(List<pumplist> list, Context context, Dashboard.OnPumpAddListener addListener) {
        this.list = list;
        this.context = context;
        this.addListener = addListener;
    }



    @NonNull
    @Override
    public Mycustomerdashboard.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pumplist_customer, parent, false);
        Myviewholder holder = new Myviewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Mycustomerdashboard.Myviewholder holder, int position) {
        holder.Pumpname.setText(list.get(position).getName());
        holder.Address.setText(list.get(position).getAddress());
        holder.Time.setText(list.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView Pumpname,Address,Time;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            Pumpname =itemView.findViewById(R.id.pumpname);
            Address =itemView.findViewById(R.id.pumpadd);
            Time =itemView.findViewById(R.id.openhours);

        }
    }
}
