package com.example.simra_000.user_application.Restaurant;

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

public class Restaurant_List extends AppCompatActivity {


        ListView list2;
        // ArrayList<String>  a1=new ArrayList<>();
        // ArrayList<Integer> a2=new ArrayList<>();  /* instead of using 2 arraylist we can use listmodel as below

        String c_id="";

        final ArrayList<Restaurant_Model> mymodel = new ArrayList<>();
        Restaurant_Adapter adapter1;
        ArrayList<Restaurant_Model> data1 = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_restaurant__list);

            list2=(ListView)findViewById(R.id.list2);
            c_id= sharedpreference.getCity_id(Restaurant_List.this);

            new abc().execute();
           // adapter= new Restaurant_Adapter(Restaurant_List.this,mymodel);
           // list2.setAdapter(adapter);
            list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    Toast.makeText(Restaurant_List.this, ""+mymodel.get(i).getName(), Toast.LENGTH_SHORT).show();

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

    class abc extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "";

        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(Restaurant_List.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            data1.clear();

            String response = "";
            String url=getResources().getString(R.string.url);

            try {
                response = run(url+"action=restaurant&c_id=" +c_id);
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
                    String res_image = jobj1.getString("Image");
                    String rating = jobj1.getString("Rating");
                    String place = jobj1.getString("Place");


                    Restaurant_Model mymodel=new Restaurant_Model(id,c_id,name,place,res_image,rating);
                    data1.add(mymodel);


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

                adapter1 = new Restaurant_Adapter(Restaurant_List.this,data1,"normal");
                list2.setAdapter(adapter1);

            } else {
                Toast.makeText(Restaurant_List.this, "some problem....", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }

