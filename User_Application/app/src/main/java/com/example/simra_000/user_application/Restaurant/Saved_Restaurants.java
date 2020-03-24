package com.example.simra_000.user_application.Restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.arraylist_sharedpreference;

import java.util.ArrayList;

public class Saved_Restaurants extends AppCompatActivity {
    ListView wish1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved__restaurants);

        wish1=(ListView)findViewById(R.id.wish1);

        ArrayList<Restaurant_Model> h2=new ArrayList<Restaurant_Model>();
        h2= arraylist_sharedpreference.getRestaurant(Saved_Restaurants.this);
        Restaurant_Adapter adapter = new Restaurant_Adapter(Saved_Restaurants.this,h2,"save");
        wish1.setAdapter(adapter);

        //h2=arraylist_sharedpreference.UpdateCart_Order(Saved_Restaurants.this);

       // final ArrayList<Restaurant_Model> finalH = h2;
       /* wish1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Saved_Restaurants.this);
                builder1.setMessage("Are you sure you want to delete?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                h2.remove(position);
                                arraylist_sharedpreference.Update_Restaurant(Saved_Restaurants.this, h2);
                                Restaurant_Adapter adapter = new Restaurant_Adapter(Saved_Restaurants.this,h2,"save");
                                wish1.setAdapter(adapter);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return false;
            }
        });*/
    }
}
