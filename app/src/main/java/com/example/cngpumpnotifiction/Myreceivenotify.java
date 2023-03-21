package com.example.cngpumpnotifiction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Myreceivenotify extends RecyclerView.Adapter<Myreceivenotify.Myviewholder>{
    List<notifymessage>list;
    Context context;

    public Myreceivenotify(List<notifymessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myreceivenotify.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_list,parent,false);
        Myreceivenotify.Myviewholder holder = new Myreceivenotify.Myviewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myreceivenotify.Myviewholder holder, int position) {
        holder.message.setText(list.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        EditText message;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);

        }
    }
}
