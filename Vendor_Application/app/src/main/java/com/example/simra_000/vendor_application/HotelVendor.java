package com.example.simra_000.vendor_application;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class HotelVendor extends AppCompatActivity {

    // private static final String TAG = "EmailPassword";
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<String> mResults1 = new ArrayList<>();
    ViewPager pager, pager1;
    //Spinner city;
    //ListView listview;

    Toolbar toolbar;
    EditText edit_hotelname, latitude, longtitude, amnities, price, place, rating, room_name, multi_hoteldescription;
    //TextView text_hotelcontactinfo,text_hotellocation,textTitleHotel,text_hotelmap;
    //ListView listview_hotel;
    Button hotelphoto, roomphoto, upload, city;
    //private static final int SELECT_FILE = 100;
    public static final int PERMISSION_ALL = 200;
    String imagePath = "", imageStatus, status;
    //private static final int SELECT_FILE = 100;
    ImageView iv_image, room_image;
    OkHttpClient client = new OkHttpClient();
    String hid = "";
    int flag = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_vendor);

        //city=(Spinner)findViewById(R.id.city);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edit_hotelname = (EditText) findViewById(R.id.edit_hotelname);
        city = (Button) findViewById(R.id.city);
        place = (EditText) findViewById(R.id.place);
        latitude = (EditText) findViewById(R.id.latitude);
        longtitude = (EditText) findViewById(R.id.longtitude);
        amnities = (EditText) findViewById(R.id.amnities);
        price = (EditText) findViewById(R.id.price);
        rating = (EditText) findViewById(R.id.rating);
        room_name = (EditText) findViewById(R.id.room_name);

        pager = (ViewPager) findViewById(R.id.pager);
        pager1 = (ViewPager) findViewById(R.id.pager1);
        //edit_hotelpopularplace=(EditText)findViewById(R.id.edit_hotelpopularplace);

        multi_hoteldescription = (EditText) findViewById(R.id.multi_hoteldescription);
        // multi_hoteladdress=(MultiAutoCompleteTextView)findViewById(R.id.multi_hoteladdress);

        hotelphoto = (Button) findViewById(R.id.hotelphoto);
        roomphoto = (Button) findViewById(R.id.roomphoto);
        upload = (Button) findViewById(R.id.upload);

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HotelVendor.this, city);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(HotelVendor.this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        String selected_city_id = "";

                        if (item.getTitle().toString().equals("Surat")) {
                            selected_city_id = "1";
                        } else if (item.getTitle().toString().equals("Baroda")) {
                            selected_city_id = "7";
                        } else if (item.getTitle().toString().equals("Mumbai")) {
                            selected_city_id = "2";
                        } else if (item.getTitle().toString().equals("Pune")) {
                            selected_city_id = "3";
                        }
                        // Shared Prefrence for City_Id
                        sharedpreference.setCity_id(HotelVendor.this, selected_city_id);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        // text_hotelmap=(TextView)findViewById(R.id.text_hotelmap);

        //iv_image = (ImageView) findViewById(R.id.iv_image);
        //room_image = (ImageView) findViewById(R.id.room_image);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new abc().execute();
            }
        });

        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        hotelphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResults.size() >= 3) {
                    Toast.makeText(HotelVendor.this, "Only Threee Image allow", Toast.LENGTH_SHORT).show();
                } else {
                    // Click on add button
                    flag = 1;
                    new ImagePicker.Builder(HotelVendor.this)
                            .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                            .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                            .directory(ImagePicker.Directory.DEFAULT)
                            .extension(ImagePicker.Extension.PNG)
                            .scale(600, 600)
                            .allowMultipleImages(true)
                            .enableDebuggingMode(true)
                            .build();
                }
            }
        });

        roomphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click on add button
                flag = 2;
                new ImagePicker.Builder(HotelVendor.this)
                        .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.PNG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build();

            }
        });
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //Multiple Image Start
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {

            if (flag == 1) {
                ArrayList<String> mpath = (ArrayList<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);

                for (int i = 0; i < mpath.size(); i++) {
                    mResults.add(mpath.get(i));
                }
                //Your Code
                //   iv_image.setImageBitmap(BitmapFactory.decodeFile(mResults.get(0)));

                PageAdapter mAdapter = new PageAdapter(HotelVendor.this, mResults);
                pager.setAdapter(mAdapter);
                flag = 0;
            } else if (flag == 2) {
                ArrayList<String> mpath = (ArrayList<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);

                for (int i = 0; i < mpath.size(); i++) {
                    mResults1.add(mpath.get(i));
                }
                //Your Code
                //   iv_image.setImageBitmap(BitmapFactory.decodeFile(mResults.get(0)));

                PageAdapter mAdapter = new PageAdapter(HotelVendor.this, mResults1);
                pager1.setAdapter(mAdapter);
                flag = 0;

            }
            // tvResults.setText(sb.toString());
        }
        //  Toast.makeText(this, ""+filePath, Toast.LENGTH_SHORT).show();
//            image_view.setImageBitmap(bitmap_image);

    }


    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class UploadImage extends AsyncTask<Void, Void, String> {

        String id = "";

        public UploadImage(String id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {

                if (mResults.size() >= 1) {
                    String res = new Upload().uploadFile(mResults.get(0), getResources().getString(R.string.url) + "action=uploadImage_Hotel1&id=" + id, "photo", HotelVendor.this);
                    Log.d("image Upload URl", getResources().getString(R.string.url) + "?action=uploadImage_Hotel1&id=" + id);
                    Log.d("image Upload Res", res);
                }
                if (mResults.size() >= 2) {
                    String res1 = new Upload().uploadFile(mResults.get(1), getResources().getString(R.string.url) + "action=uploadImage_Hotel2&id=" + id, "photo", HotelVendor.this);
                    Log.d("image Upload URl1", getResources().getString(R.string.url) + "?action=uploadImage_Hotel2&id=" + id);
                    Log.d("image Upload Res1", res1);
                }
                if (mResults.size() >= 3) {
                    String res2 = new Upload().uploadFile(mResults.get(2), getResources().getString(R.string.url) + "action=uploadImage_Hotel3&id=" + id, "photo", HotelVendor.this);
                    Log.d("image Upload URl2", getResources().getString(R.string.url) + "?action=uploadImage_Hotel3&id=" + id);
                    Log.d("image Upload Res2", res2);
                }


            } catch (Exception e) {
                return e.getMessage().toString();
            }
            return "valid";
        }
    }

    class UploadImage1 extends AsyncTask<Void, Void, String> {

        String id1 = "";

        public UploadImage1(String id1) {
            this.id1 = id1;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {

                if (mResults1.size() >= 1) {
                    String res = new Upload().uploadFile(mResults1.get(0), getResources().getString(R.string.url) + "action=uploadImage_Room&id=" + id1, "photo", HotelVendor.this);
                    Log.d("image Upload URl", getResources().getString(R.string.url) + "?action=uploadImage_Room&id=" + id1);
                    Log.d("image Upload Res", res);
                }

            } catch (Exception e) {
                return e.getMessage().toString();
            }
            return "valid";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(HotelVendor.this, s, Toast.LENGTH_SHORT).show();
            if (s.equals("valid")) {
                finish();

            } else {
                Toast.makeText(HotelVendor.this, "Something goes wrong...Plz try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Multiple Image Finish
    //declare second service herw

    class xyz extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "";
        String id = "";
        String id1 = "";


        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(HotelVendor.this);
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
                response = run(url + "action=Vendorhotel_details&hid=" + hid + "&Room_Name=" + room_name.getText().toString() + "&Hotel_Description=" + multi_hoteldescription.getText().toString() + "&Room_Image=img2.png&Image1=img2.png&Image2=img2.png&Price=" + price.getText().toString() + "&Amnities=" + amnities.getText().toString() + "&RoomImage=img2.png&Latitude=" + latitude.getText().toString() + "&Longtitude=" + longtitude.getText().toString());
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
                Toast.makeText(HotelVendor.this, "Uploaded Seccesfull....", Toast.LENGTH_SHORT).show();
                new UploadImage(id).execute();
                new UploadImage1(id1).execute();

                //Intent i = new Intent(HotelVendor.this, Login.class);
                //startActivity(i);
                //new xyz().execute();
                //  finish();
                finish();
            } else
                try {

                    //Intent i=new Intent(Register.this,Login.class);
                    //startActivity(i);


                    if (res.equals("valid")) {
                        Toast.makeText(HotelVendor.this, "added successfully!", Toast.LENGTH_SHORT).show();

                        // finish();
                    } else {
                        Toast.makeText(HotelVendor.this, "Try Again", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    Toast.makeText(HotelVendor.this, "Some Error....", Toast.LENGTH_SHORT).show();
                }
        }
    }

    class abc extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "";


        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(HotelVendor.this);
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
                response = run(url + "action=Vendor_hotel&Name=" + edit_hotelname.getText().toString() + "&Rating=" + rating.getText().toString() + "&Image=img1.png&Place=" + place.getText().toString() + "&c_id=1");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");


                JSONObject obj = jarray.getJSONObject(0);
                res = obj.getString("status");
                hid = obj.optString("id");


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
                //Toast.makeText(HotelVendor.this, "Uploaded Seccesfull....", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(HotelVendor.this, Login.class);
                //startActivity(i);
                new xyz().execute();
                //  finish();

            } else {
                Toast.makeText(HotelVendor.this, "Some Error....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}