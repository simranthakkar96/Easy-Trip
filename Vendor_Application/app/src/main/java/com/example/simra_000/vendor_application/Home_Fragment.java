package com.example.simra_000.vendor_application;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home_Fragment extends Fragment {
    CardView hotel,restaurant,guide;
    int mflipping = 0;  // Initially flipping is off
    int mFlipping = 0 ;

    public Home_Fragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home__fragment,container,false);


        hotel=(CardView)view.findViewById(R.id.hotel);
        restaurant=(CardView)view.findViewById(R.id.restaurant);
        guide=(CardView)view.findViewById(R.id.guide);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),HotelVendor.class);
                startActivity(i);
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Restaurant_Vendor.class);
                startActivity(i);
            }
        });

        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Travel_Guide.class);
                startActivity(i);
            }
        });
    return  view;

    }
}
