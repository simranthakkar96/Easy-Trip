package com.example.simra_000.user_application.packing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.simra_000.user_application.R;

public class Lesiure_Trip extends AppCompatActivity {
CardView swiming,snow,gym,photo,beach,bicycle,running,motorcycle,dinner,international,camp,essentails1,toiletaries1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesiure__trip);

        swiming=(CardView) findViewById(R.id.swiming);
        swiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","1");
                startActivity(i);

            }
        });

        snow=(CardView) findViewById(R.id.snow);
        snow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","2");
                startActivity(i);

            }
        });
        gym=(CardView) findViewById(R.id.gym);
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","4");
                startActivity(i);

            }
        });
        photo=(CardView) findViewById(R.id.photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","5");
                startActivity(i);

            }
        });
        beach=(CardView) findViewById(R.id.beach);
        beach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","7");
                startActivity(i);

            }
        });
        bicycle=(CardView) findViewById(R.id.bicycle);
        bicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","8");
                startActivity(i);

            }
        });
        running=(CardView) findViewById(R.id.running);
        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","9");
                startActivity(i);

            }
        });
        motorcycle=(CardView) findViewById(R.id.motorcycle);
        motorcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","11");
                startActivity(i);

            }
        });
        dinner=(CardView) findViewById(R.id.dinner);
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","10");
                startActivity(i);

            }
        });
        international=(CardView)findViewById(R.id.international);
        international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","6");
                startActivity(i);

            }
        });

        camp=(CardView)findViewById(R.id.camp);
        camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","3");
                startActivity(i);

            }
        });

        essentails1=(CardView)findViewById(R.id.essentials1);
        essentails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","12");
                startActivity(i);

            }
        });

        toiletaries1=(CardView)findViewById(R.id.toiletaries1);
        toiletaries1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Lesiure_Trip.this,Pack_List.class);
                i.putExtra("id","13");
                startActivity(i);

            }
        });
    }
}
