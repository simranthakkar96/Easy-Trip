package com.example.simra_000.vendor_application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Login extends AppCompatActivity {

    EditText uname, pass;
    Button login, signup;
    OkHttpClient client = new OkHttpClient();

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uname.getText().toString().trim().equals("")) {
                    uname.setError("Enter  Username");
                    uname.setFocusable(true);
                } else if (pass.getText().toString().trim().equals("")) {
                    pass.setError("Enter Password");
                    pass.setFocusable(true);
                } else {
                    new xyz().execute();
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });
    }

    String run(String url) throws IOException
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class xyz extends AsyncTask<String, Void, String>
    {
        ProgressDialog progressDialog;
        String res = "";
        String id = "";

        @Override

        protected void onPreExecute()
        {
            try
            {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String response = "";
            String url=getResources().getString(R.string.url);

            try {
                response = run(url+"action=getlogin&Fname=" + uname.getText().toString() + "&Password=" + pass.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try
            {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");

                JSONObject obj1 = jarray.getJSONObject(0);
               // res = obj1.getString("status");
                id = obj1.optString("id");

                if (jarray.length() > 0)
                {
                    res = "valid";
                }
                else
                {
                    res = "invalid";
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();

                Log.d("he33", e.getMessage().toString());
                return "error";
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (res.equals("valid"))
            {
                Toast.makeText(Login.this, "Seccesfull Login....", Toast.LENGTH_SHORT).show();
                sharedpreference.setUser_id(Login.this,id);
                Intent i = new Intent(Login.this, HotelVendor.class);
                startActivity(i);
                finish();
            }
            else
            {
                Toast.makeText(Login.this, "Username or Password Invalid....", Toast.LENGTH_SHORT).show();
            }
        }

    }
}


