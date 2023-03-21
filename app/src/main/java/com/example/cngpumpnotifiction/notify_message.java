package com.example.cngpumpnotifiction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link notify_message#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notify_message extends Fragment {
        EditText pump_id,message,updated_by;
        Button Addnotify;
        DBhelper helper;
        SharedPreferences sharpref;
        SharedPreferences.Editor editor;
        Dashboard activity;
        RecyclerView notirecy;
        Myreceivenotify adpter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public notify_message() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notify_message.
     */
    // TODO: Rename and change types and number of parameters
    public static notify_message newInstance(String param1, String param2) {
        notify_message fragment = new notify_message();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (Dashboard)getActivity();

        if (getArguments() != null) {
            mParam1 = getArguments().getString("id");   //////pump_id
            mParam2 = getArguments().getString("Id");
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notify_message, container, false);
        message = view.findViewById(R.id.pump_msg);
        pump_id = view.findViewById(R.id.pump_id);
        updated_by = view.findViewById(R.id.pump_update);
        notirecy = view.findViewById(R.id.notirecy);
        Addnotify = view.findViewById(R.id.add_notify);
        helper = new DBhelper(getContext());
        sharpref = activity.getSharedPreferences("Pump", Context.MODE_PRIVATE);
        editor = sharpref.edit();

//           String id = sharpref.getString("id","[]");

        Addnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                      String update = sharpref.getString("Id", "NA");
//                  helper.addnotify(message.getText().toString(),updated_by.getText().toString(),pump_id.getText().toString());
                    helper.addnotify(message.getText().toString(), sharpref.getString("email","[]"), mParam1);       ////mpharma1 is id of pump id which is shared from pump table and add in argument

            }
        });

        Myreceivenotify adpter = new Myreceivenotify(helper.getnotify(),getContext());
        notirecy.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        notirecy.setVisibility(View.VISIBLE);
        notirecy.setAdapter(adpter);


        return view;


    }
}