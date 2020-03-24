package com.example.simra_000.user_application.Hotel;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.sharedpreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Hotel_List extends AppCompatActivity
{

        ListView list1;

        // ArrayList<String>  a1=new ArrayList<>();
        // ArrayList<Integer> a2=new ArrayList<>();  /* instead of using 2 arraylist we can use listmodel as below
        final ArrayList<List_Model> mymodel = new ArrayList<>();
        ArrayList<List_Model> data = new ArrayList<>();
        List_Adapter adapter;

        String c_id="";


        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hotel__list);

            list1 = (ListView) findViewById(R.id.list1);
            //Shared Prefrence
            // Also add when we want to filter
            c_id= sharedpreference.getCity_id(Hotel_List.this);
          //  Toast.makeText(this, ""+c_id, Toast.LENGTH_SHORT).show();
            new xyz().execute();
          //  adapter = new List_Adapter(Hotel_List.this, mymodel);
            //list1.setAdapter(adapter);



            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    Toast.makeText(Hotel_List.this, "" + mymodel.get(i).getName(), Toast.LENGTH_SHORT).show();
                }
            });

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
        String res = "";

        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(Hotel_List.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            data.clear();

            String response = "";
            String url=getResources().getString(R.string.url);

            try {
                response = run(url+"action=hotel&c_id=" +c_id);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jobj1 = jarray.getJSONObject(i);
                    String id = jobj1.getString("id");
                    String c_id=jobj1.getString("c_id");
                    String name = jobj1.getString("Name");
                    String hotel_image = jobj1.getString("Image");
                    String rating = jobj1.getString("Rating");
                    String place = jobj1.getString("Place");


                    List_Model model=new List_Model(id,c_id,name,hotel_image,rating,place);
                    data.add(model);


                }

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

                List_Adapter adapter = new List_Adapter(Hotel_List.this, data,"normal");
                list1.setAdapter(adapter);

            } else {
                Toast.makeText(Hotel_List.this, "some problem....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

