package com.example.simra_000.user_application.Hotel;

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
 * Created by simra_000 on 03-03-2018.
 */

public class List_Adapter extends BaseAdapter
{
    Context context;
    // ArrayList<String> name=new ArrayList<>();
    //ArrayList<Integer> image1=new ArrayList<>();

    ArrayList<List_Model> list =new ArrayList<>();
    String type;

    public List_Adapter(Context context, ArrayList<List_Model> list,String type)
    {
        this.context = context;
        this.list = list;
        this.type = type;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.hotel_list_item_1,viewGroup,false);

        TextView text,place;
        ImageView image,delete;
        RatingBar rate_img;
        Button prices;

        text=(TextView) view.findViewById(R.id.mytext);
        image=(ImageView) view.findViewById(R.id.myimage);
        rate_img=(RatingBar) view.findViewById(R.id.rate_img);
        place=(TextView)view.findViewById(R.id.place);
        prices = (Button) view.findViewById(R.id.prices);
        delete = (ImageView) view.findViewById(R.id.delete);

        if(type.equals("save")){
            delete.setVisibility(View.VISIBLE);
        }
        else {
            delete.setVisibility(View.GONE);
        }

        delete.setTag(i);
        delete.setOnClickListener(new View.OnClickListener() {
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
                                list.remove(position);
                                arraylist_sharedpreference.Update_Hotel(context,list);
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

        text.setText(list.get(i).getName());

        Picasso.with(context)
                .load(context.getResources().getString(R.string.imagepath)+list.get(i).getImage())
                .placeholder(R.drawable.tgb)
                .into(image);

       // image.setImageResource(Integer.parseInt(list.get(i).getImage()));
        rate_img.setRating(Float.parseFloat(list.get(i).getRating()));
        rate_img.setEnabled(false);
        place.setText(list.get(i).getPlace());

        prices.setTag(i);
        prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int pos=(int)v.getTag();
                Intent intent =new Intent(context,Hotel_Page.class);
                intent.putExtra("hid",list.get(pos).getId());

                //to show the hotel image of list in main hotel page
                intent.putExtra("hotelmodel",list.get(pos));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
