package com.example.simra_000.user_application.Travel_Guide;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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

public class Travel_Guide extends AppCompatActivity {
    GridView travel_guide;
    Button book_guide;

    // ArrayList<String>  a1=new ArrayList<>();
    // ArrayList<Integer> a2=new ArrayList<>();  /* instead of using 2 arraylist we can use listmodel as below
    final ArrayList<Travel_Guide_Model> mymodel = new ArrayList<>();
    ArrayList<Travel_Guide_Model> data2= new ArrayList<>();
    Travel_Guide_Adapter adapter;

    String c_id="";
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel__guide);

        book_guide=(Button)findViewById(R.id.book_guide);
        travel_guide = (GridView) findViewById(R.id.travel_guide);

        c_id= sharedpreference.getCity_id(Travel_Guide.this);

        adapter = new Travel_Guide_Adapter(Travel_Guide.this, mymodel);
        travel_guide.setAdapter(adapter);

        new xyz().execute();
        travel_guide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(Travel_Guide.this, "" + mymodel.get(i).getAgency(), Toast.LENGTH_SHORT).show();
            }
        });

        /*book_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Travel_Guide.this,Travel_Guide_Book.class);
                startActivity(i);
            }
        });*/

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

        data2.clear();

        String response = "";
        String url=getResources().getString(R.string.url);

        try {
            response = run(url+"action=travel_guide&c_id=" +c_id);
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
                String agency = jobj1.getString("Agency");
                String agency_address = jobj1.getString("Agency_Address");
                String guide_name = jobj1.getString("Guide_Name");
                String contact = jobj1.getString("Contact");


                Travel_Guide_Model model=new Travel_Guide_Model(id,c_id,agency,agency_address,guide_name,contact);
                data2.add(model);


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

            Travel_Guide_Adapter adapter = new Travel_Guide_Adapter(Travel_Guide.this, data2);
            travel_guide.setAdapter(adapter);

        } else {
            Toast.makeText(Travel_Guide.this, "some problem....", Toast.LENGTH_SHORT).show();
        }
    }
}
}


