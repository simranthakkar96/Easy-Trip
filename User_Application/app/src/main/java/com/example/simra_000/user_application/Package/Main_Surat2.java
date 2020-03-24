package com.example.simra_000.user_application.Package;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ViewFlipper;

import com.example.simra_000.user_application.R;

import java.util.ArrayList;

public class Main_Surat2 extends AppCompatActivity {

    private static final String TAG = "Main_Surat";
    int mflipping = 0;  // Initially flipping is off
    int mFlipping = 0 ;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames1 = new ArrayList<>();
    private ArrayList<Integer> mImageUrls1 = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<Integer> mImageUrls2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__surat2);

        getImages();
        getImages1();
        getImages3();

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

        mImageUrls.add(R.drawable.place1);
        mNames.add("Subash Chandra /n Bose Aquarium");

        mImageUrls.add(R.drawable.place3);
        mNames.add("ISCKON Temple");

        mImageUrls.add(R.drawable.place5);
        mNames.add("Gopi Talav");

        mImageUrls.add(R.drawable.place8);
        mNames.add("Old Fort");

        mImageUrls.add(R.drawable.place7);
        mNames.add("Sidhi Vinayak /n Mandir");


        initRecyclerView();

    }

    private void getImages1(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls1.add(R.drawable.place4);
        mNames1.add("VR Mall");

        mImageUrls1.add(R.drawable.place6);
        mNames1.add("Science Centre");

        initRecyclerView1();

    }

    private void getImages3(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls2.add(R.drawable.place9);
        mNames2.add("Surat Station");

        initRecyclerView2();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(Main_Surat2.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Main_Surat2.this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
    private void initRecyclerView1(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(Main_Surat2.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Main_Surat2.this, mNames1, mImageUrls1);
        recyclerView.setAdapter(adapter);
    }
    private void initRecyclerView2(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(Main_Surat2.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Main_Surat2.this, mNames2, mImageUrls2);
        recyclerView.setAdapter(adapter);
    }
}
