package com.example.simra_000.user_application.packing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simra_000.user_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PAck_List_Leisure extends AppCompatActivity {
    TextView heading1,text2;
    //Button start;
    OkHttpClient client;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack__list__leisure);

        heading1=(TextView)findViewById(R.id.heading1);
        text2=(TextView)findViewById(R.id.text2);

        id = getIntent().getStringExtra("id");

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
        String res = "",id,heading2,items;

        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(PAck_List_Leisure.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            //   data.clear();

            String response = "";
            String url=getResources().getString(R.string.url);

            try {
                response = run(url+ "action=pack_list_business&id=" +id);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");


                JSONObject jobj1 = jarray.getJSONObject(0);
                //String pid = jobj1.getString("id");
                // String heading=jobj1.getString("heading");
                // String items = jobj1.getString("items");
                id = jobj1.getString("id");
                heading2 = jobj1.getString("heading");
                items = jobj1.getString("items");
                //Pack_Model model=new Pack_Model(pid,heading,items);
                //data.add(model);


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

                heading1.setText(heading2);
                text2.setText(items);

            } else {
                Toast.makeText(PAck_List_Leisure.this, "some problem....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
