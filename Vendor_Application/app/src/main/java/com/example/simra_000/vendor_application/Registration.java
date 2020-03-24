package com.example.simra_000.vendor_application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Registration extends AppCompatActivity
{

    EditText fname,lname,pass,cpass,email,number;
    Button but1;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        pass = (EditText) findViewById(R.id.pass);
        cpass = (EditText) findViewById(R.id.cpass);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.number);
        but1 = (Button) findViewById(R.id.but1);

       // Spinner spin = (Spinner) findViewById(R.id.spinner);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vendor, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(adapter);

        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (fname.getText().toString().trim().equals(""))
                {
                    fname.setError("Please Enter Ur First Name");
                    fname.setFocusable(true);
                }
                else if (lname.getText().toString().trim().equals(""))
                {
                    lname.setError("Please Enter Ur Last Name");
                    lname.setFocusable(true);
                }
                else if (pass.getText().toString().trim().length() > 6)
                {
                    pass.setError("Please Enter atleast 6 digits");
                    pass.setFocusable(true);
                }
                else if (!pass.getText().toString().trim().equals(cpass.getText().toString().trim()))
                {
                    cpass.setError("Incorrect Password");
                    cpass.setFocusable(true);
                }
                else if (email.getText().toString().trim().equals(""))
                {
                    email.setError("Please Enter Ur Email");
                    email.setFocusable(true);
                }
                else if (!emailValidator(email.getText().toString().trim()))
                {
                    email.setError("Enter Valid Email");
                    email.setFocusable(true);
                }
                else if (number.getText().toString().trim().length() < 10)
                {
                    number.setError("Please Enter Atleast 10 numbers");
                    number.setFocusable(true);
                }
                else
                {
                    new abc().execute();
                }
            }
        });
    }

    /* Copy Pasted From Net*/
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
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
                progressDialog = new ProgressDialog(Registration.this);
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
            String url=getResources().getString(R.string.url);

            try {
                response = run(url+"action=register&Fname=" + fname.getText().toString() + "&Lname=" + lname.getText().toString() + "&Pass=" + pass.getText().toString() + "&cpass=" + cpass.getText().toString() + "&Email=" + email.getText().toString() + "&Mobile=" + number.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");



                JSONObject obj = jarray.getJSONObject(0);
                res = obj.getString("status");



            } catch (JSONException e) {
                e.printStackTrace();

                Log.d("he33",e.getMessage().toString());
                return "error";
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (res.equals("valid")) {
                Toast.makeText(Registration.this, "Registeration Seccesfull....", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(Registration.this, "Username already exist....", Toast.LENGTH_SHORT).show();
            }
        }

    }
}



