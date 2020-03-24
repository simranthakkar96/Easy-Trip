package com.example.simra_000.user_application.Restaurant;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.arraylist_sharedpreference;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Restaurant_Page extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

    TextView Name, phone, cusines, hours, info, cost;
    ImageView menu_image, restaurant_image;
    WebView res_map;
    Button save;
    Restaurant_Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__page);

        restaurant_image = (ImageView)findViewById(R.id.restaurant_image);
        menu_image=(ImageView)findViewById(R.id.menu_image);
        Name=(TextView)findViewById(R.id.Name);
        phone=(TextView)findViewById(R.id.phone);
        cusines=(TextView)findViewById(R.id.cusines);
        hours=(TextView)findViewById(R.id.hours);
        info=(TextView)findViewById(R.id.info);
        cost=(TextView)findViewById(R.id.cost);
        res_map=(WebView)findViewById(R.id.res_map);
        save=(Button)findViewById(R.id.save);

        model=(Restaurant_Model)getIntent().getSerializableExtra("restromodel");

        Picasso.with(Restaurant_Page.this)
                .load(getResources().getString(R.string.imagepath)+model.getRes_image())
                .placeholder(R.drawable.tgb)
                .into(restaurant_image);

        // to send a particular list item to wish list page
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Toast.makeText(Restaurant_Page.this, "Saved", Toast.LENGTH_SHORT).show();
                        //ArrayList<Restaurant_Model> h2=new ArrayList<Restaurant_Model>();
                        //h2=arraylist_sharedpreference.getRestaurant(Restaurant_Page.this);
                        //h2.add(model);
                         arraylist_sharedpreference.setRestaurant(Restaurant_Page.this,model);;
                    }
                });
        new xyz().execute();
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class xyz extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "",id,name,re_id,Price,Cusines,Hours,Phone,information,Menu_image,Menu_image1,Menu_image2,latitude ,longtitude;

        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(Restaurant_Page.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {


            String response = "";
            String url = getResources().getString(R.string.url);

            try {
                response = run(url + "action=restaurant_details&re_id=" +model.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");

                //  for (int i = 0; i < jarray.length(); i++) {
                JSONObject jobj1 = jarray.getJSONObject(0);
                 id = jobj1.getString("id");
                 name = jobj1.getString("Restaurant_Name");
                 re_id = jobj1.getString("re_id");
                 Price = jobj1.getString("Price");
                 Cusines = jobj1.getString("Cusines");
                 Hours = jobj1.getString("Opening Hours");
                 Phone = jobj1.getString("Phone Number");
                 information = jobj1.getString("Information");
                 Menu_image = jobj1.getString("Menu_Image");
                 Menu_image1 = jobj1.getString("Menu_Image1");
                 Menu_image2 = jobj1.getString("Menu_Image2");
                 latitude = jobj1.getString("Latitude");
                 longtitude = jobj1.getString("Longtitude");





                 // }

            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }
            return "valid";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("valid")) {

                Name.setText(name);
                phone.setText(Phone);
                cusines.setText(Cusines);
                hours.setText(Hours);
                info.setText(information);
                cost.setText(Price);

                Picasso.with(Restaurant_Page.this)
                        .load(Restaurant_Page.this.getResources().getString(R.string.imagepath)+Menu_image)
                        .placeholder(R.drawable.hotel5)
                        .into(menu_image);

                String url1 = "https://maps.google.com/maps?q=" + latitude+ "," +longtitude+ "&zoom=10&size=80x80&maptype=roadmap&markers=color:red|" +latitude+ "," + longtitude+ "";
                res_map.getSettings().setJavaScriptEnabled(true);
                res_map.setWebViewClient(new WebViewClient());
                res_map.loadUrl(url1);



            } else {
                Toast.makeText(Restaurant_Page.this, "some problem....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
