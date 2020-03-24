package com.example.simra_000.user_application.Hotel;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.sharedpreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Book_Hotel extends AppCompatActivity {
    private RadioButton Pay1, Pay2, Pay3;
    Button book_hotel;
    OkHttpClient client=new OkHttpClient();
    String hid="";
    String h_detailid="";
    String price="";
    String user_id="";
RadioGroup pay_type;
String selected_type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__hotel);

        book_hotel = (Button) findViewById(R.id.book_hotel);
        Pay1 = (RadioButton) findViewById(R.id.pay1);
        Pay2 = (RadioButton) findViewById(R.id.pay2);
        Pay3 = (RadioButton) findViewById(R.id.pay3);
        pay_type = (RadioGroup) findViewById(R.id.pay_type);


        if(Pay1.isChecked())
        {
            selected_type=Pay1.getText().toString();
        }
       else if(Pay2.isChecked())
        {
            selected_type=Pay2.getText().toString();
        }
        else if(Pay3.isChecked())
        {
            selected_type=Pay3.getText().toString();
        }

        pay_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(Pay1.isChecked())
                {
                    selected_type=Pay1.getText().toString();
                }
                else if(Pay2.isChecked())
                {
                    selected_type=Pay2.getText().toString();
                }
                else if(Pay3.isChecked())
                {
                    selected_type=Pay3.getText().toString();
                }

            }
        });

        addListenerOnPay1();
        addListenerOnPay2();
        addListenerOnPay3();
        addListenerOnButton();

        user_id= sharedpreference.getUser_id(Book_Hotel.this);
        hid = getIntent().getStringExtra("hid");
        h_detailid = getIntent().getStringExtra("id");
        price=getIntent().getStringExtra("price");


        //user_id=getIntent().getStringExtra("User_id");

       /* public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.pay1:
                    if (checked)
                        // Pirates are the best
                        break;
                case R.id.pay2:
                    if (checked)
                        // Ninjas rule
                        break;
            }
        }
*/
       book_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new abc().execute();
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
                progressDialog = new ProgressDialog(Book_Hotel.this);
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
                response = run(url+"action=hotel_booking&hid=" +hid+ "&user_id=" +user_id + "&h_detail_id=" +h_detailid+ "&Amount=" +price+ "&type=" +selected_type);
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


                Toast.makeText(Book_Hotel.this, "Booked ", Toast.LENGTH_SHORT).show();


                //Intent i = new Intent(Travel_Guide_Book.this, Login.class);
                //startActivity(i);
                finish();

            } else {
                Toast.makeText(Book_Hotel.this, "Not Available at these dates", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void addListenerOnPay1() {

        Pay1 = (RadioButton) findViewById(R.id.pay1);

        Pay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((RadioButton) v).isChecked()) {
                    Toast.makeText(Book_Hotel.this, "Paytm Selected for payment", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void addListenerOnPay2() {

        Pay2 = (RadioButton) findViewById(R.id.pay2);

        Pay2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((RadioButton) v).isChecked()) {
                    Toast.makeText(Book_Hotel.this, "Credit Card Selected Selected for payment", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void addListenerOnPay3() {

        Pay3 = (RadioButton) findViewById(R.id.pay3);

        Pay3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((RadioButton) v).isChecked()) {
                    Toast.makeText(Book_Hotel.this, "Debit Card Selected Selected for payment", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void addListenerOnButton() {

        Pay1 = (RadioButton) findViewById(R.id.pay1);
        Pay2 = (RadioButton) findViewById(R.id.pay2);
        Pay3 = (RadioButton) findViewById(R.id.pay3);
        //btnDisplay = (Button) findViewById(R.id.btnDisplay);

        book_hotel.setOnClickListener(new View.OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {

                Toast.makeText(Book_Hotel.this, "Hotel Booked", Toast.LENGTH_LONG).show();

            }
        });

    }


}
