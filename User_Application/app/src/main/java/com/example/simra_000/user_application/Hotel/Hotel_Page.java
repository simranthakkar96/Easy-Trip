package com.example.simra_000.user_application.Hotel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simra_000.user_application.R;
import com.example.simra_000.user_application.arraylist_sharedpreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Hotel_Page extends AppCompatActivity {

    //int year, monthOfYear, dayOfMonth;
    int day, month, year;
    int day1, month1, year1;
    Calendar myCalendar, calendar;
    ListView hotellist;
    String hid = "";

    ViewPager pager;

    ArrayList<String> image = new ArrayList<>();
    ListView listofcomplains1;

    final ArrayList<Hotel_Page_Model> model1 = new ArrayList<>();
    ArrayList<Hotel_Page_Model> data = new ArrayList<>();
    Hotel_Page_Adapter adapter;
    List_Model model;
    WebView hotelmap;
    RatingBar rate_img;
    ImageView hotel_image;
    TextView hotel_description;
    Button save_hotel;
    EditText date1;
    ArrayList<String> images = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__page);
        //findcontrol();

        pager=(ViewPager)findViewById(R.id.pager);

        hotellist = (ListView) findViewById(R.id.hotellist);
        hotelmap = (WebView) findViewById(R.id.hotelmap);
        rate_img = (RatingBar) findViewById(R.id.rate_img);
       // hotel_image = (ImageView) findViewById(R.id.hotel_image);
        hotel_description = (TextView) findViewById(R.id.hotel_description);
        save_hotel = (Button) findViewById(R.id.save_hotel);

        final EditText date1 = (EditText) findViewById(R.id.date1);
        final EditText date2 = (EditText) findViewById(R.id.date2);

        hid = getIntent().getStringExtra("hid"); // to see the content of the same hotel on which it is clicked

        // to see the image of list in main page (Written after the i.putExtra is written in hotel_list
        model = (List_Model) getIntent().getSerializableExtra("hotelmodel");

       /* Picasso.with(Hotel_Page.this)
                .load(getResources().getString(R.string.imagepath) + model.getImage())
                .placeholder(R.drawable.tgb)
                .into(hotel_image);*/

        images.clear();
        images.add(model.getImage());
        new xyz().execute();


        hotellist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                //   Toast.makeText(Hotel_Page.this, "" + model1.get(i).getName(), Toast.LENGTH_SHORT).show();

            }
        });

        /*final DatePickerDialog.OnDateSetListener date3 = new DatePickerDialog.OnDateSetListener() {
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
                date2.setText(sdf.format(myCalendar.getTime()));
            }
        };*/

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        month = month + 1;
        date1.setText(day + "/" + month + "/" + year);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(Hotel_Page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        date1.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
                pickerDialog.show();


                //new DatePickerDialog(Hotel_Page.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        myCalendar = Calendar.getInstance();
        day1 = myCalendar.get(Calendar.DAY_OF_MONTH);
        month1 = myCalendar.get(Calendar.MONTH);
        year1 = myCalendar.get(Calendar.YEAR);

        month1 = month1 + 1;
        date2.setText(day1 + "/" + month1 + "/" + year1);

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(Hotel_Page.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month1 = month1 + 1;
                        date2.setText(dayOfMonth + "/" + month1 + "/" + year);
                    }
                }, year1, month1, day1);
                pickerDialog.show();


                //new DatePickerDialog(Hotel_Page.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Hotel_Page.this, date3, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        // to go in whish list
        save_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Hotel_Page.this, "Saved", Toast.LENGTH_SHORT).show();
                // ArrayList<List_Model> h1=new ArrayList<List_Model>();
                //  h1=arraylist_sharedpreference.getHotel(Hotel_Page.this);
                //h1.add(model);
                arraylist_sharedpreference.setHotel(Hotel_Page.this, model);
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
                progressDialog = new ProgressDialog(Hotel_Page.this);
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
            String url = getResources().getString(R.string.url);

            try {
                response = run(url + "action=hotel_details&hid=" + hid);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jobj1 = jarray.getJSONObject(i);
                    String h_detail_id = jobj1.getString("id");
                    String hid = jobj1.getString("hid");
                    String name = jobj1.getString("Name");
                    String hotel_description = jobj1.getString("Hotel_Description");
                    String image = jobj1.getString("Image");
                    String image1 = jobj1.getString("Image1");
                    String image2 = jobj1.getString("Image2");
                    String amnities = jobj1.getString("Amnities");
                    String price = jobj1.getString("Price");
                    String latitude = jobj1.getString("Latitude");
                    String longtitude = jobj1.getString("Longtitude");

                    images.add(image);
                   // images.add(image1);
                    //images.add(image2);

                    Hotel_Page_Model model = new Hotel_Page_Model(h_detail_id, name, hotel_description, image,image1,image2, amnities, price, hid, latitude, longtitude);
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

                ////images.add(Hotel_Page_Model.getHotel_image());


                PageAdapter adapter1 = new PageAdapter(Hotel_Page.this, images);
                pager.setAdapter(adapter1);

                Hotel_Page_Adapter adapter = new Hotel_Page_Adapter(Hotel_Page.this, data);
                hotellist.setAdapter(adapter);

                String url1 = "https://maps.google.com/maps?q=" + data.get(0).getLatitude() + "," + data.get(0).getLongtitde() + "&zoom=10&size=80x80&maptype=roadmap&markers=color:red|" + data.get(0).getLatitude() + "," + data.get(0).getLongtitde() + "";
                hotelmap.getSettings().setJavaScriptEnabled(true);
                hotelmap.setWebViewClient(new WebViewClient());
                hotelmap.loadUrl(url1);

                hotel_description.setText(data.get(0).getHotel_description());
                //String url = "https://maps.googleapis.com/maps/api/staticmap?center=" + data.get(0).getLatitude() + "," + data.get(0).getLongtitde()+ "&zoom=13&size=350x200&maptype=roadmap&markers=color:red|" + data.get(0).getLatitude() + "," + data.get(0).getLongtitde() + "";
                //Log.d("img1", "longilatitude");
                //holder.webview.getSettings().setJavaScriptEnabled(true);
                //holder.webview.loadUrl(url);

            } else {
                Toast.makeText(Hotel_Page.this, "Some problem....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
