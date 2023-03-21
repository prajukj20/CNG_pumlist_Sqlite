package com.example.cngpumpnotifiction;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button pumplist, Infulance;
    public OnPumpAddListener addListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pumplist = findViewById(R.id.pumplist);
        Infulance = findViewById(R.id.intrest);

        pumplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.dashfrem, new customer_dashboard());
                transaction.commit();
            }
        });


    }


    interface OnPumpAddListener {
        void onItemAdded(pumplist data);

        void onItemUpdate(String name, String time);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof register_page) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                for (Fragment fragment1 : getSupportFragmentManager().getFragments()) {
                    if (fragment1 instanceof register_page || fragment1 instanceof customer_dashboard || fragment1 instanceof notify_message)
                        transaction.remove(fragment1);
                }
                transaction.commit();
                return;

            }

            for (Fragment fragmen2 : getSupportFragmentManager().getFragments()) {
                if (fragmen2 instanceof customer_dashboard) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.remove(fragmen2);
                    transaction.commit();
                    return;
                }
            }



            for (Fragment fragmen3 : getSupportFragmentManager().getFragments()) {
                if (fragmen3 instanceof notify_message) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.remove(fragmen3);
                    transaction.commit();
                    return;
                }
            }



        }
    }
}