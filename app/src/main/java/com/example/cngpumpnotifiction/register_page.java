package com.example.cngpumpnotifiction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register_page extends Fragment {
    Spinner spineer1;
    List<String>typelist;
    EditText email_id,password;
    Button Register;
    DBhelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public register_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register_page.
     */
    // TODO: Rename and change types and number of parameters
    public static register_page newInstance(String param1, String param2) {
        register_page fragment = new register_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_page, container, false);

        spineer1 = view.findViewById(R.id.spinner);
        email_id = view.findViewById(R.id.mail);
        password = view.findViewById(R.id.password);
        Register = view.findViewById(R.id.login);
        helper = new DBhelper(getContext());
        typelist = new ArrayList<>();
        typelist.add("Owner");
        typelist.add("Influrance");
        typelist.add("Customer");
        spineer1.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,typelist));


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email_id = email_id.getText().toString();

                if (Email_id.isEmpty()){
                    Toast.makeText(getContext(),"Email_id or Mobile_no not Exits",Toast.LENGTH_LONG).show();
                 return;
                }else {
                    List<userdetails>list =helper.getregisterUserByEmail(Email_id);
                    if (list.size()>0){
                        Toast.makeText(getContext(),"You are alrady Registered.",Toast.LENGTH_LONG).show();
                        //go to login page
                    }else {
                        helper.addregister(email_id.getText().toString(),password.getText().toString(),spineer1.getSelectedItem().toString());
                        Toast.makeText(getContext(), "User Registered Successfully.", Toast.LENGTH_SHORT).show();

                }}


//                String Password = password.getText().toString();
//                helper.addregister(Email_id,Password,spineer1.getSelectedItem().toString());

            }
        });


        return view;
    }

    public void update(String printable_text){
        Log.e("TAG", "test: "+ printable_text);
    }
}