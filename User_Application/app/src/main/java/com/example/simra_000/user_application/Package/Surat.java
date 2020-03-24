package com.example.simra_000.user_application.Package;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.R;

public class Surat extends AppCompatActivity {
CardView pack1,pack2,pack3;
ViewFlipper flipper2;
    int mflipping = 0;  // Initially flipping is off
    int mFlipping = 0 ;

    //ViewFlipper flipper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat);


        pack1=(CardView)findViewById(R.id.pack1);
        pack2=(CardView)findViewById(R.id.pack2);
        pack3=(CardView)findViewById(R.id.pack3);

        //flipper2=(ViewFlipper)findViewById(R.id.flipper2);

        pack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Surat.this,Main_Surat.class);
                startActivity(i);
            }
        });

        pack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Surat.this,Main_Surat2.class);
                startActivity(i);
            }
        });


        ViewFlipper flipper = (ViewFlipper)findViewById(R.id.flipper2);
        /** Start Flipping */
        flipper.startFlipping();
        mFlipping = 1;

        ViewFlipper Flipper = (ViewFlipper)findViewById(R.id.flipper2);
        /** Start Flipping */
        Flipper.startFlipping();
        mflipping = 1;
        //return inflater.inflate(R.layout.home_fragment,null);

    }
}
