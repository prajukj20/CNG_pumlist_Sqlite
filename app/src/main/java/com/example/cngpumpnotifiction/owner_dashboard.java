package com.example.cngpumpnotifiction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link owner_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class owner_dashboard extends Fragment {
    EditText Pumpname,Address,Location_LAN,time,user_id;
    Button Save;
    DBhelper helper;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Dashboard activity;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public owner_dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment owner_dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static owner_dashboard newInstance(String param1, String param2) {
        owner_dashboard fragment = new owner_dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (Dashboard) getActivity();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_owner_dashboard, container, false);
        Pumpname = view.findViewById(R.id.pumpname);
        Address = view.findViewById(R.id.pumpadd);
        Location_LAN = view.findViewById(R.id.LANmark);
        time = view.findViewById(R.id.openhours);
        Save = view.findViewById(R.id.save);
        user_id = view.findViewById(R.id.user_id);
        helper = new DBhelper(getContext());
        sharedPref =activity.getSharedPreferences("Pump", Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pumpname = Pumpname.getText().toString();
                String address = Address.getText().toString();
                String location = Location_LAN.getText().toString();
                String Time = time.getText().toString();

                if (pumpname.isEmpty()||address.isEmpty()||location.isEmpty()||Time.isEmpty()){
                    Toast.makeText(getContext(),"Please enter All details",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    List<pumplist>list = helper.getregisterpump();
                    if (list.size()==0){

                    String id =sharedPref.getString("id","NA");

                        helper.addpump(Pumpname.getText().toString(),Address.getText().toString(),Location_LAN.getText().toString(),time.getText().toString(),id);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.add(R.id.ownerpay,new owner_profileupdatedashboard());
                        transaction.commit();
                    }
                }

            }
        });


        return view;
    }
}