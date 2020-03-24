package com.example.simra_000.user_application.Package;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.Package.Kerela;
import com.example.simra_000.user_application.Package.Kolkata;
import com.example.simra_000.user_application.Package.Mumbai;
import com.example.simra_000.user_application.Package.Rajasthan;
import com.example.simra_000.user_application.Package.Shimla;
import com.example.simra_000.user_application.Package.Surat;
import com.example.simra_000.user_application.R;

public class Chat_Fragment extends Fragment {
CardView city1,city2,city3,city4,city5,city6;
    public Chat_Fragment()
    {

    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat__fragment, container, false);

        city1 = (CardView) view.findViewById(R.id.city1);
        city2 = (CardView) view.findViewById(R.id.city2);
        city3 = (CardView) view.findViewById(R.id.city3);
        city4 = (CardView) view.findViewById(R.id.city4);
        city5 = (CardView) view.findViewById(R.id.city5);
        city6 = (CardView) view.findViewById(R.id.city6);


        city1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Surat.class);
                startActivity(i);
            }
        });

        city2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Mumbai.class);
                startActivity(i);
            }
        });


        city3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Kerela.class);
                startActivity(i);
            }
        });


        city4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Kolkata.class);
                startActivity(i);
            }
        });

        city5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Shimla.class);
                startActivity(i);
            }
        });

        city6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),Rajasthan.class);
                startActivity(i);
            }
        });
        return view;
        //return inflater.inflate(R.layout.home_fragment,null);
    }
}
