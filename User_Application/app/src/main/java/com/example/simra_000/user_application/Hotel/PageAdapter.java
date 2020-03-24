package com.example.simra_000.user_application.Hotel;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.simra_000.user_application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pragma1 on 09/09/2016.
 */
public class PageAdapter extends PagerAdapter {

    Context contex;

    LayoutInflater inflater;

    ArrayList<String> img1 =new ArrayList<>();






    public PageAdapter(Context contex, ArrayList<String> img1) {
        this.contex=contex;
        this.img1 = img1;
        inflater= LayoutInflater.from(contex);
    }

    @Override
    public int getCount() {
        return img1.size();
    }


        public static class ViewHolder {


            public ImageView imageView;

        }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.admin_single_pager_adapter, container, false);

         ViewHolder holder = new ViewHolder();


        holder.imageView = (ImageView) itemView.findViewById(R.id.image);

    //  Bitmap  myBitmap = BitmapFactory.decodeFile(img1.get(position));
     //   holder.imageView.setImageBitmap(myBitmap);

        Picasso.with(contex)
                .load(contex.getResources().getString(R.string.imagepath)+""+img1.get(position))
                .placeholder(R.drawable.tgb)
                .resize(300,300)
                .into(holder.imageView);


        container.addView(itemView);

         itemView.setTag(holder);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}
