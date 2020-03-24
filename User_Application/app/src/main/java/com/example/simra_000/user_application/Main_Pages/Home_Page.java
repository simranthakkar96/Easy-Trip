package com.example.simra_000.user_application.Main_Pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.simra_000.user_application.Package.Chat_Fragment;
import com.example.simra_000.user_application.Profile.Profile_Fragment;
import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.sharedpreference;


public class Home_Page extends AppCompatActivity
{




    Toolbar toolbar;
    Button city;
    ImageView news;

    BottomNavigationView bottomNavigationView;
    LinearLayout main_frame;

    private Home_Fragment homeFragment;
    private Profile_Fragment profileFragment;
    private Chat_Fragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MaterialSearchView materialsearchview;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

       // logout = (Button) findViewById(R.id.logout);
        city = (Button) findViewById(R.id.city);
        news=(ImageView)findViewById(R.id.news);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.til.indiatimes");
                if (intent != null) {
                    // We found the activity now start the activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=com.til.indiatimes"));
                    startActivity(intent);
                }
            }
        });




        //homeFragment = new Home_Fragment();
        //profileFragment = new Profile_Fragment();

        // Next 3 lines For Fragment
        main_frame=(LinearLayout)findViewById(R.id.main_frame);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        //bottomNavigationView.setOnNavigationItemSelectedListener(this);
        ReplaceFragment(new Home_Fragment()); // it determines which page should be default




        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreference.clearAll(Home_Page.this);
                Intent i = new Intent(Home_Page.this, Login.class);
                startActivity(i);
                finish();
            }
        });*/

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Home_Page.this, city);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Home_Page.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        String selected_city_id="";

                        if(item.getTitle().toString().equals("Surat")){
                            selected_city_id="1";
                        }
                        else if(item.getTitle().toString().equals("Baroda")){
                            selected_city_id="7";
                        }
                        else if(item.getTitle().toString().equals("Mumbai")){
                            selected_city_id="2";
                        }
                        else if(item.getTitle().toString().equals("Pune")){
                            selected_city_id="3";
                        }
                        // Shared Prefrence for City_Id
                        sharedpreference.setCity_id(Home_Page.this,selected_city_id);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.searchbar);
        setSupportActionBar(toolbar);


        //bottombar = BottomBar.attach(this, savedInstanceState);
      /*  bottombar.setItemsFromMenu(R.menu.bottom_items, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId==R.id.bottomitem)
                {
                    BottomFragment i = new BottomFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomframe,i).commit();
                }
                else if(menuItemId==R.id.bottomitem2)
                {
                    Profile_Fragment i = new Profile_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomframe,i).commit();
                }
                else if(menuItemId==R.id.bottomitem3)
                {
                    Navigation_Fragment i = new Navigation_Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottomframe,i).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
*/
        /* Changes Bottom Bar color whenever any button is clicked*/

        //  bottombar.mapColorForTab(0,"#f44336");
        //  bottombar.mapColorForTab(0,"#9c27b0");
        //  bottombar.mapColorForTab(0,"#e65100");

       // bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
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
                            case R.id.bottomitem3:
                                chatFragment=new Chat_Fragment();
                                ReplaceFragment(chatFragment);
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
