package com.example.simra_000.user_application.Profile;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.simra_000.user_application.Main_Pages.Login;
import com.example.simra_000.user_application.R;

/**
 * Created by simra_000 on 03-04-2018.
 */

public class Profile_Fragment extends Fragment
{
    TextView edit;
    TextView wishlist,working,contact;
    Button logout;
    public Profile_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_fragment,container,false);

        edit=(TextView)view.findViewById(R.id.edit);
        wishlist=(TextView)view.findViewById(R.id.wishlist);
        working=(TextView)view.findViewById(R.id.working);
        contact=(TextView)view.findViewById(R.id.contact);
        logout=(Button)view.findViewById(R.id.signout);


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),WishList.class);
                startActivity(i);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Page_Update.class);
                startActivity(i);
            }
        });
        working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Works.class);
                startActivity(i);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ContactUs.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Login.class);
                startActivity(i);
            }
        });



        logout=(Button) view.findViewById(R.id.signout);
        return view;
        //return inflater.inflate(R.layout.profile_fragment,null);
    }
}
