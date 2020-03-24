package com.example.simra_000.user_application.Hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.arraylist_sharedpreference;

import java.util.ArrayList;

public class Saved_Hotels extends AppCompatActivity {
    ListView wish;
    ArrayList<List_Model> h1 = new ArrayList<List_Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved__hotels);

        wish = (ListView) findViewById(R.id.wish);


        h1 = arraylist_sharedpreference.getHotel(Saved_Hotels.this);
        //h1.remove(0);
        //h1.remove(1);


        List_Adapter adapter = new List_Adapter(Saved_Hotels.this, h1,"save");
        wish.setAdapter(adapter);

        /*wish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Saved_Hotels.this, "Hello delee it", Toast.LENGTH_SHORT).show();
            }
        });

        wish.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Saved_Hotels.this, "long", Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

      /*wish.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Saved_Hotels.this);
                builder1.setMessage("Are you sure you want to delete?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                h1.remove(position);
                                arraylist_sharedpreference.Update_Hotel(Saved_Hotels.this,h1);
                                List_Adapter adapter = new List_Adapter(Saved_Hotels.this,h1);
                                adapter.notifyDataSetChanged();
                                wish.setAdapter(adapter);
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
          public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                //Do your tasks here


                AlertDialog.Builder alert = new AlertDialog.Builder(
                        Saved_Hotels.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        h1.remove(position);
                        arraylist_sharedpreference.Update_Hotel(Saved_Hotels.this,h1);
                        List_Adapter adapter = new List_Adapter(Saved_Hotels.this,h1);
                      //  adapter.notifyDataSetChanged();
                        wish.setAdapter(adapter);
                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();

                return true;
            }
        });
}*/
    }
}
