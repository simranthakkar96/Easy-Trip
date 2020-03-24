package com.example.simra_000.user_application.Main_Pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.sharedpreference;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mythread=new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(5000);
                    Intent i=new Intent(Splash.this,Login.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                    String userid="";
                    userid= sharedpreference.getUser_id(Splash.this);
                    if (userid.equals("no")){
                        Intent i = new Intent(Splash.this, Login.class);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(Splash.this, Home_Page.class);
                        startActivity(i);
                    }
                }
            }
        };mythread.start();
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}





