package com.example.simra_000.user_application.Travel_Guide;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simra_000.user_application.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Travel_Guide_Book extends AppCompatActivity {

    int years, monthOfYear, dayOfMonths;
    Button availablity;
    OkHttpClient client = new OkHttpClient();
    String guide_id="";
    EditText from,to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel__guide__book);

        guide_id = getIntent().getStringExtra("guide_id"); // to see the content of the same guide on which it is clicked

         from = (EditText) findViewById(R.id.from);
         to = (EditText) findViewById(R.id.to);
        availablity=(Button)findViewById(R.id.availablity);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date7 = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, years);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonths);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/mm/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                from.setText(sdf.format(myCalendar.getTime()));
            }
        };

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date5 = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/mm/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                to.setText(sdf.format(myCalendar.getTime()));
            }
        };

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(Travel_Guide_Book.this, date7, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Travel_Guide_Book.this, date5, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        availablity.setOnClickListener(new View.OnClickListener() {
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
                progressDialog = new ProgressDialog(Travel_Guide_Book.this);
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
                response = run(url+"action=guide_book&guide_id=" + guide_id + "&from=" + from.getText().toString() + "&to=" + to.getText().toString());
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
                Toast.makeText(Travel_Guide_Book.this, " Available ", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(Travel_Guide_Book.this, Login.class);
                //startActivity(i);
                finish();

            } else {
                Toast.makeText(Travel_Guide_Book.this, "No One Available at these dates", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
