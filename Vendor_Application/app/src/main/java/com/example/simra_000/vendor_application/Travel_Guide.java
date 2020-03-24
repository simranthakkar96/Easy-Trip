package com.example.simra_000.vendor_application;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Travel_Guide extends AppCompatActivity {
EditText agency_name,guide_name,number,guideaddress;
Button upload2;
String id="";
OkHttpClient client= new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel__guide);

        agency_name=(EditText)findViewById(R.id.agency_name);
        guide_name=(EditText)findViewById(R.id.guide_name);
        number=(EditText)findViewById(R.id.number);
        guideaddress=(EditText)findViewById(R.id.guideaddress);

        upload2=(Button)findViewById(R.id.upload2);

        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new abc().execute();
            }
        });
    }
    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url)
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
                progressDialog = new ProgressDialog(Travel_Guide.this);
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
                response = run(url + "action=guide&Agency=" + agency_name.getText().toString() + "&Agency_Address=" + guideaddress.getText().toString()+ "&Guide_Name=" + guide_name.getText().toString()+ "&Contact=" + number.getText().toString() + "&c_id=1");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");


                JSONObject obj = jarray.getJSONObject(0);
                res = obj.getString("status");
                id = obj.optString("id");


            } catch (JSONException e) {
                e.printStackTrace();

                Log.d("he33", e.getMessage().toString());
                return "error";
            }
            return res;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (res.equals("valid")) {
                Toast.makeText(Travel_Guide.this, "Uploaded Seccesfull....", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(HotelVendor.this, Login.class);
                //startActivity(i);
                //new HotelVendor.xyz().execute();
                 finish();

            } else {
                Toast.makeText(Travel_Guide.this, "Some Error....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
