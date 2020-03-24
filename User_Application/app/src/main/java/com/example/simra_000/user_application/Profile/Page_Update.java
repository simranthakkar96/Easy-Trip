package com.example.simra_000.user_application.Profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simra_000.user_application.Main_Pages.Home_Page;
import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.sharedpreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Page_Update extends AppCompatActivity {
    EditText UFname, ULname, UEmail, UMobile, UE_pwd, UC_pwd;
    Button uUpdate;
    //RadioButton male,female;
    String id = "",gender;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page__update);

        id= sharedpreference.getUser_id(Page_Update.this);
        Toast.makeText(Page_Update.this, ""+id, Toast.LENGTH_SHORT).show();
        UFname = (EditText)findViewById(R.id.uFname);
        //UMname = (EditText)findViewById(R.id.uMname);
        ULname = (EditText)findViewById(R.id.uLname);
        //radio = (RadioGroup) view.findViewById(R.id.radio);
        //male = (RadioButton) view.findViewById(R.id.male);
        //female = (RadioButton) view.findViewById(R.id.female);
        UEmail = (EditText)findViewById(R.id.uEmail);
        UMobile = (EditText)findViewById(R.id.uMobile);
        UE_pwd = (EditText)findViewById(R.id.uEpwd);
        UC_pwd = (EditText)findViewById(R.id.uCpwd);
        //UAddress = (EditText)findViewById(R.id.uAddres);
        uUpdate = (Button)findViewById(R.id.uUpdate);

        new getdata().execute();


        uUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(male.isChecked()){
                //  gender="Male";
                //}
                //else{
                // gender="Female";
                //}
                if (UFname.getText().toString().trim().equals("")) {
                    UFname.setError("Please enter first name");
                    UFname.requestFocus();
                } else if (ULname.getText().toString().trim().equals("")) {
                    ULname.setError("Please enter Last name");
                    ULname.requestFocus();
                } else if (UEmail.getText().toString().trim().equals("")) {
                    UEmail.setError("Please enter your Email");
                    UEmail.requestFocus();
                } else if (!isvalidemail(UEmail.getText().toString().trim())) {
                    UEmail.setError("Please enter valid Email");
                    UEmail.requestFocus();
                } else if (UMobile.getText().toString().trim().equals("")) {
                    UMobile.setError("Please enter your mobile no.");
                    UMobile.requestFocus();
                } else if (UE_pwd.getText().toString().trim().equals("")) {
                    UE_pwd.setError("Please enter your password");
                    UE_pwd.requestFocus();
                } else if (UE_pwd.getText().toString().trim().length() < 6 || UE_pwd.getText().toString().trim().length() > 15) {
                    UE_pwd.setError("Password must be in 6 to 15 digit");
                    UE_pwd.requestFocus();
                } else if (UC_pwd.getText().toString().trim().equals("")) {
                    UC_pwd.setError("Please enter confirm password");
                    UC_pwd.requestFocus();
                } else if (!UE_pwd.getText().toString().trim().equals(UC_pwd.getText().toString().trim())) {
                    UC_pwd.setError("your password does not match");
                    UC_pwd.requestFocus();
                } else {
                    new updatedata().execute();

                }
            }
        });
       // return view;
    }
    private boolean isvalidemail(String email)
    {
        //String email_ptn="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email_ptn = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        Pattern pattern = Pattern.compile(email_ptn);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }


    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public class getdata extends AsyncTask<Void, String, String> {


        String fname, mname, lname, emai, phone, addre, pasword;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = getString(R.string.url);
            String response = "";
            try {
                response = run(url+"action=getregistration&id="+ id);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");


                // for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jobj1 = jsonArray.getJSONObject(0);
                fname = jobj1.getString("fname");
                //mname = jobj1.getString("middle_name");
                lname = jobj1.getString("lname");
                //gender = jobj1.getString("gender");
                emai = jobj1.getString("email");
                phone = jobj1.getString("mobile");
                //addre = jobj1.getString("user_address");
                pasword = jobj1.getString("pass");


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

            if (s.equals("valid")) {

                UFname.setText(fname);
                //UMname.setText(mname);
                ULname.setText(lname);
                //if (gender.equalsIgnoreCase("Female")){
                //  female.setChecked(true);
                //}
                //else{
                //  male.setChecked(true);
                //}
                UEmail.setText(emai);
                UMobile.setText(phone);
                UE_pwd.setText(pasword);
                UC_pwd.setText(pasword);
                //UAddress.setText(addre);

            } else {
                Toast.makeText(Page_Update.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        }


    }

    public class updatedata extends AsyncTask<Void, String, String> {


        String status="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = getString(R.string.url);
            String response = "";
            try {
                response = run(url+"action=updateuser_application&id=" + id+"&fname="+UFname.getText().toString()+"&lname="+ULname.getText().toString()+"&pass="+UE_pwd.getText().toString()+"&email="+UEmail.getText().toString()+"&mobile="+UMobile.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");

                // for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jobj1 = jsonArray.getJSONObject(0);
                status = jobj1.getString("staus");



                // }
            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

            return status;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("valid")) {


                uUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Page_Update.this, "Update Succefully...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Page_Update.this,Home_Page.class);
                        startActivity(i);

                    }
                });

            } else {

                Toast.makeText(Page_Update.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        }


    }
}

