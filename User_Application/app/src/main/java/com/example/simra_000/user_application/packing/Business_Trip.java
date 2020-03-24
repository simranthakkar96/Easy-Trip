package com.example.simra_000.user_application.packing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.simra_000.user_application.R;

public class Business_Trip extends AppCompatActivity {

    CardView working,international1,dinner1,casual,essentials,toiletaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__trip);

        working=(CardView)findViewById(R.id.working);
        international1=(CardView)findViewById(R.id.international1);
        dinner1=(CardView)findViewById(R.id.dinner1);
        casual=(CardView)findViewById(R.id.casual);
       // formal=(CardView)findViewById(R.id.formal);
        essentials=(CardView)findViewById(R.id.essentials);
        toiletaries=(CardView)findViewById(R.id.toiletaries);

        working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });

        international1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });

        dinner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","6");
                startActivity(intent);
            }
        });

        casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });

        essentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        toiletaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Business_Trip.this,Pack_List.class);
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });


    }
}
