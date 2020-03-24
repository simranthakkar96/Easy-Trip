package com.example.simra_000.user_application.Restaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.arraylist_sharedpreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by simra_000 on 04-03-2018.
 */

public class Restaurant_Adapter extends BaseAdapter
{
    Context context;
    // ArrayList<String> name=new ArrayList<>();
    //ArrayList<Integer> image1=new ArrayList<>();

    ArrayList<Restaurant_Model> list2 =new ArrayList<>();
    String type1;

    public Restaurant_Adapter(Context context, ArrayList<Restaurant_Model> list2,String type1)
    {
        this.context = context;
        this.list2 = list2;
        this.type1=type1;
    }


    @Override
    public int getCount() {
        return list2.size();
    }

    @Override
    public Object getItem(int i) {
        return list2.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.restaurant_list_item,viewGroup,false);

        TextView name,place1;
        ImageView res_image,delete1;
        RatingBar rating ;
        Button price;

        name=(TextView) view.findViewById(R.id.name);
        res_image=(ImageView) view.findViewById(R.id.res_image);
        rating=(RatingBar) view.findViewById(R.id.rating);
        place1=(TextView)view.findViewById(R.id.place1);
        price= (Button) view.findViewById(R.id.price);
        delete1=(ImageView)view.findViewById(R.id.delete1);

        if(type1.equals("save")){
            delete1.setVisibility(View.VISIBLE);
        }
        else {
            delete1.setVisibility(View.GONE);
        }

        delete1.setTag(i);
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position=(int)v.getTag();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                list2.remove(position);
                                arraylist_sharedpreference.Update_Restaurant(context,list2);
                                notifyDataSetChanged();

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
            }
        });


        Picasso.with(context)
                .load(context.getResources().getString(R.string.imagepath)+list2.get(i).getRes_image())
                .placeholder(R.drawable.tgb)
                .into(res_image);

        name.setText(list2.get(i).getName());
       // myimage.setImageResource(Integer.parseInt(list.get(i).getImage()));
        rating.setRating(Float.parseFloat(list2.get(i).getRating()));
        rating.setEnabled(false);
        place1.setText(list2.get(i).getPlace());
        price.setTag(i);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int pos=(int)v.getTag();
                Intent intent =new Intent(context,Restaurant_Page.class);
                intent.putExtra("restromodel",list2.get(pos));
                context.startActivity(intent);
            }
        });
        return view;
    }

}
