package com.example.simra_000.user_application.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.R;

public class Works extends AppCompatActivity implements View.OnClickListener{
    Button previous,next;
    ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works);

        previous = (Button)findViewById(R.id.previous);
        next=(Button)findViewById(R.id.next);
        flipper=(ViewFlipper)findViewById(R.id.flipper);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v==next)
        {
            flipper.showNext();
        }
        else if(v==previous)
        {
            flipper.showPrevious();
        }
    }
}
