package com.example.simra_000.user_application.Package;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.R;

import java.util.ArrayList;

public class Main_Surat extends AppCompatActivity {
    private static final String TAG = "Main_Surat";
    int mflipping = 0;  // Initially flipping is off
    int mFlipping = 0 ;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__surat);

        getImages();

        ViewFlipper flipper= (ViewFlipper)findViewById(R.id.flipper2);
        /** Start Flipping */
        flipper.startFlipping();
        mFlipping = 1;

        ViewFlipper Flipper = (ViewFlipper)findViewById(R.id.flipper2);
        /** Start Flipping */
        Flipper.startFlipping();
        mflipping = 1;
        //return inflater.inflate(R.layout.home_fragment,null);

    }
    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add(R.drawable.place2);
        mNames.add("Dumas Beach");

        mImageUrls.add(R.drawable.place3);
        mNames.add("ISCKON Temple");

        mImageUrls.add(R.drawable.place4);
        mNames.add("VR Mall");

        mImageUrls.add(R.drawable.place5);
        mNames.add("Gopi Talav");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Main_Surat.this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
}

