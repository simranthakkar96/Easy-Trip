package com.example.simra_000.user_application.Travel_Guide;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.simra_000.user_application.R;

import java.util.ArrayList;

/**
 * Created by simra_000 on 01-04-2018.
 */

public class Travel_Guide_Adapter extends BaseAdapter
{
        Context context;
        // ArrayList<String> name=new ArrayList<>();
        //ArrayList<Integer> image1=new ArrayList<>();

        ArrayList<Travel_Guide_Model> travel_guide =new ArrayList<>();

public Travel_Guide_Adapter(Context context, ArrayList<Travel_Guide_Model> travel_guide)
        {
        this.context = context;
        this.travel_guide = travel_guide;
        }


@Override
public int getCount() {
        return travel_guide.size();
        }

@Override
public Object getItem(int i) {
        return travel_guide.get(i);
        }

@Override
public long getItemId(int i) {
        return i;
        }

@Override
public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.travel_guide_list_item,viewGroup,false);

        TextView agency,address,guide_name,guide_contact;
        Button book_guide;


        agency=(TextView) view.findViewById(R.id.agency);
        address=(TextView) view.findViewById(R.id.address);
        guide_name=(TextView) view.findViewById(R.id.guide_name);
        guide_contact=(TextView) view.findViewById(R.id.guide_contact);
        book_guide=(Button) view.findViewById(R.id.book_guide);

        agency.setText(travel_guide.get(i).getAgency());
        address.setText(travel_guide.get(i).getAddress());
        guide_name.setText(travel_guide.get(i).getGuide_name());
        guide_contact.setText(travel_guide.get(i).getGuide_contact());

        book_guide.setTag(i);   // To go to next page according to the guide id clicked
        book_guide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int pos=(int)v.getTag();
                        Intent intent =new Intent(context,Travel_Guide_Book.class);
                        intent.putExtra("guide_id",travel_guide.get(pos).getId()); // to go to next page according to the id
                        context.startActivity(intent);
                }
        });

        return view;
        }
}
