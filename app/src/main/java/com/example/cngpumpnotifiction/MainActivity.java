package com.example.cngpumpnotifiction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button Register, login;
    DBhelper dBhelper;
    EditText mail;
    EditText password;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String email, Password;
//    MainActivity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBhelper = new DBhelper(MainActivity.this);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        Register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        sharedPref = getSharedPreferences("Pump", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        email = sharedPref.getString("email_key", null);
        Password = sharedPref.getString("password_key", null);

        if (sharedPref.getBoolean("login_status", false) == true) {
            if (sharedPref.getString("type", "").equalsIgnoreCase("Owner")) {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            } else if (sharedPref.getString("type", "").equalsIgnoreCase("Customer")) {

            } else if (sharedPref.getString("type", "").equalsIgnoreCase("Influencer")) {

            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = mail.getText().toString();
                if (email_id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Email_id or Mobile_no", Toast.LENGTH_SHORT).show();
                    return;
                } else {
//                    List<userdetails> list = dBhelper.getregisterUserByEmail(id);
                    List<userdetails> list = dBhelper.getregisterUserByEmail(email_id);
                    if (list.size() == 0) {
                        Toast.makeText(MainActivity.this, "User dosn't exits", Toast.LENGTH_LONG).show();
                    } else {
                        if (list.get(0).password.equalsIgnoreCase(password.getText().toString())) {
                            editor.putString("id", list.get(0).getId().toString());
                            editor.putString("email", list.get(0).getEmail().toString());
                            editor.putString("password", list.get(0).getPassword().toString());
                            editor.putString("type", list.get(0).getType().toString());
                            editor.putBoolean("login_status", true);     /////Start page without login
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                        }


                    }

                }
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.rgisterframe, new register_page());
                transaction.commit();
            }
        });
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