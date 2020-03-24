package com.example.simra_000.vendor_application;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class Home_Page extends AppCompatActivity{
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    LinearLayout main_frame;

    private Home_Fragment homeFragment;
    private Profile_Fragment profileFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //MaterialSearchView materialsearchview;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        //bottomNavigationView.setOnNavigationItemSelectedListener(this);
        ReplaceFragment(new Home_Fragment()); // it determines which page should be default

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.bottomitem:
                                homeFragment=new Home_Fragment();
                                ReplaceFragment(homeFragment);
                                break;
                            case R.id.bottomitem2:
                                profileFragment=new Profile_Fragment();
                                ReplaceFragment(profileFragment);
                                break;
                        }

                        return true;
                    }
                });
    }

    public void ReplaceFragment(android.app.Fragment fragment)
    {
        // Create new fragment and transaction
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.main_frame, fragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}

