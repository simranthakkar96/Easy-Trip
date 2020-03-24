package com.example.simra_000.user_application.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simra_000.user_application.Hotel.Saved_Hotels;
import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.Restaurant.Saved_Restaurants;

public class WishList extends AppCompatActivity {
    Button saved_hotel,saved_restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        saved_hotel=(Button)findViewById(R.id.saved_hotels);
        saved_restaurant=(Button)findViewById(R.id.saved_restaurants);

        saved_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WishList.this,Saved_Hotels.class);
                startActivity(i);
            }
        });
        saved_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WishList.this,Saved_Restaurants.class);
                startActivity(i);
            }
        });


    }
}
