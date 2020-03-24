package com.example.simra_000.user_application.Main_Pages;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.Hotel.Hotel_List;
import com.example.simra_000.user_application.GPS.MapsActivity;
import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.Restaurant.Restaurant_List;
import com.example.simra_000.user_application.Transport;
import com.example.simra_000.user_application.Travel_Guide.Travel_Guide;
import com.example.simra_000.user_application.packing.Pack_Main_Page;

/**
 * Created by simra_000 on 03-04-2018.
 */

public class Home_Fragment extends Fragment
{
    CardView hotel,restaurant,nearby,transport,guide,holiday;
    int mflipping = 0;  // Initially flipping is off
    int mFlipping = 0 ;

    public Home_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment,container,false);

        hotel = (CardView) view.findViewById(R.id.hotel);
        restaurant = (CardView) view.findViewById(R.id.restaurant);
        nearby = (CardView) view.findViewById(R.id.nearby);
        transport = (CardView) view.findViewById(R.id.transport);
        guide=(CardView)view.findViewById(R.id.guide);
        holiday=(CardView)view.findViewById(R.id.holiday);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Hotel_List.class);
                startActivity(i);
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Restaurant_List.class);
                startActivity(i);
            }
        });


        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
            }
        });


        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Transport.class);
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

        holiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),Pack_Main_Page.class);
                startActivity(i);
            }
        });

        ViewFlipper flipper = (ViewFlipper) view.findViewById(R.id.flipper1);
        /** Start Flipping */
        flipper.startFlipping();
        mFlipping = 1;

        ViewFlipper Flipper = (ViewFlipper) view.findViewById(R.id.flipper2);
        /** Start Flipping */
        Flipper.startFlipping();
        mflipping = 1;

        return view;
        //return inflater.inflate(R.layout.home_fragment,null);
    }
}
