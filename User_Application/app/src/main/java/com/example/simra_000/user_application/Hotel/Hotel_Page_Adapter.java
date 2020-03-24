package com.example.simra_000.user_application.Hotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simra_000.user_application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by simra_000 on 29-03-2018.
 */

public class Hotel_Page_Adapter extends BaseAdapter
{
    Context context;
    // ArrayList<String> name=new ArrayList<>();
    //ArrayList<Integer> image1=new ArrayList<>();

    ArrayList<Hotel_Page_Model> data =new ArrayList<>();

    public Hotel_Page_Adapter(Context context, ArrayList<Hotel_Page_Model> data)
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView( int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.hotelpage_listitem,viewGroup,false);

        TextView name,price,amnities,hotel_description;
        ImageView hotel_image;
        Button book;
        //MultiAutoCompleteTextView description;


        name=(TextView) view.findViewById(R.id.name);
        hotel_image=(ImageView) view.findViewById(R.id.image);
        amnities=(TextView)view.findViewById(R.id.amnities);
        price=(TextView)view.findViewById(R.id.hotelprice);
        book = (Button) view.findViewById(R.id.book);
        //hotel_description =(TextView) view.findViewById(R.id.hotel_description);


        name.setText(data.get(i).getName());

        Picasso.with(context)
                .load(context.getResources().getString(R.string.imagepath)+data.get(i).getHotel_image())
                .placeholder(R.drawable.hotel5)
                .into(hotel_image);

        // image.setImageResource(Integer.parseInt(list.get(i).getImage()));
        amnities.setText(data.get(i).getAmnities());
        price.setText(data.get(i).getPrice());
        //hotel_description.setText(data.get(i).getHotel_description());
        book.setTag(i);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int pos=(int)v.getTag();
                Intent intent =new Intent(context,Book_Hotel.class);
                intent.putExtra("id",data.get(pos).getId());
                intent.putExtra("hid",data.get(pos).getHid());
                intent.putExtra("price",data.get(pos).getPrice());
                context.startActivity(intent);

            }
        });


        return view;
    }
}
