package com.example.simra_000.vendor_application;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.simra_000.vendor_application.HotelVendor.hasPermissions;


public class Restaurant_Vendor extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    EditText editresname, multidescription, editphonenumber, latitude1, longtitude1, res_address, rating1, hours, editavgcost, editcuisines;
    Button resphoto, menu_photo, upload1, city1;
    int flag = 0;

    private ArrayList<String> mResults2 = new ArrayList<>();
    private ArrayList<String> mResults3 = new ArrayList<>();

    ViewPager pager2, pager3;
    // ImageView iv_image_res, iv_image_res_menu;

    public static final int PERMISSION_ALL = 200;
    String imagePath = "", imageStatus, status;

    OkHttpClient client = new OkHttpClient();
    String reid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant__vendor);

        city1 = (Button) findViewById(R.id.city1);

        editresname = (EditText) findViewById(R.id.editresname);
        multidescription = (EditText) findViewById(R.id.multidescription);
        editphonenumber = (EditText) findViewById(R.id.editphonenumber);
        latitude1 = (EditText) findViewById(R.id.latitude1);
        longtitude1 = (EditText) findViewById(R.id.longtitude1);
        res_address = (EditText) findViewById(R.id.res_address);
        rating1 = (EditText) findViewById(R.id.rating1);
        hours = (EditText) findViewById(R.id.hours);
        editavgcost = (EditText) findViewById(R.id.hours);
        editcuisines = (EditText) findViewById(R.id.editcuisines);

        resphoto = (Button) findViewById(R.id.resphoto);
        menu_photo = (Button) findViewById(R.id.menu_photo);
        upload1 = (Button) findViewById(R.id.upload1);

        pager2 = (ViewPager) findViewById(R.id.pager2);
        pager3 = (ViewPager) findViewById(R.id.pager3);


        //  iv_image_res = (ImageView) findViewById(R.id.iv_image_res);
        // iv_image_res_menu = (ImageView) findViewById(R.id.iv_image_res_menu);

        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new abc().execute();
            }
        });

        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        resphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResults2.size() >= 3) {
                    Toast.makeText(Restaurant_Vendor.this, "Only Threee Image allow", Toast.LENGTH_SHORT).show();
                } else {
                    // Click on add button
                    flag = 1;
                    new ImagePicker.Builder(Restaurant_Vendor.this)
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

        menu_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResults3.size() >= 3) {
                    Toast.makeText(Restaurant_Vendor.this, "Only Threee Image allow", Toast.LENGTH_SHORT).show();
                } else {
                    // Click on add button
                    flag = 2;
                    new ImagePicker.Builder(Restaurant_Vendor.this)
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
                    mResults2.add(mpath.get(i));
                }
                //Your Code
                //   iv_image.setImageBitmap(BitmapFactory.decodeFile(mResults.get(0)));

                PageAdapter mAdapter = new PageAdapter(Restaurant_Vendor.this, mResults2);
                pager2.setAdapter(mAdapter);
                // tvResults.setText(sb.toString());
                flag = 0;
            }
            //  Toast.makeText(this, ""+filePath, Toast.LENGTH_SHORT).show();
//            image_view.setImageBitmap(bitmap_image);
            else if (flag == 2) {
                ArrayList<String> mpath = (ArrayList<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);

                for (int i = 0; i < mpath.size(); i++) {
                    mResults3.add(mpath.get(i));
                }
                //Your Code
                //   iv_image.setImageBitmap(BitmapFactory.decodeFile(mResults.get(0)));

                PageAdapter mAdapter = new PageAdapter(Restaurant_Vendor.this, mResults3);
                pager3.setAdapter(mAdapter);
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

                if (mResults2.size() >= 1) {
                    String res = new Upload().uploadFile(mResults2.get(0), getResources().getString(R.string.url) + "action=uploadImage_Restaurant1&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl", getResources().getString(R.string.url) + "?action=uploadImage_Restaurant1&id=" + id);
                    Log.d("image Upload Res", res);
                }
                if (mResults2.size() >= 2) {
                    String res1 = new Upload().uploadFile(mResults2.get(1), getResources().getString(R.string.url) + "action=uploadImage_Restaurant2&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl1", getResources().getString(R.string.url) + "?action=uploadImage_Restaurant2&id=" + id);
                    Log.d("image Upload Res1", res1);
                }
                if (mResults2.size() >= 3) {
                    String res2 = new Upload().uploadFile(mResults2.get(2), getResources().getString(R.string.url) + "action=uploadImage_Restaurant3&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl2", getResources().getString(R.string.url) + "?action=uploadImage_Restaurant3&id=" + id);
                    Log.d("image Upload Res2", res2);
                }


            } catch (Exception e) {
                return e.getMessage().toString();
            }
            return "valid";
        }
    }

    class UploadImage1 extends AsyncTask<Void, Void, String> {

        String id = "";

        public UploadImage1(String id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {

                if (mResults3.size() >= 1) {
                    String res = new Upload().uploadFile(mResults3.get(0), getResources().getString(R.string.url) + "action=uploadImage_Menu&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl", getResources().getString(R.string.url) + "?action=uploadImage_Menu&id=" + id);
                    Log.d("image Upload Res", res);
                }

                if (mResults3.size() >= 2) {
                    String res1 = new Upload().uploadFile(mResults3.get(1), getResources().getString(R.string.url) + "action=uploadImage_Menu2&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl1", getResources().getString(R.string.url) + "?action=uploadImage_Menu2&id=" + id);
                    Log.d("image Upload Res1", res1);
                }
                if (mResults3.size() >= 3) {
                    String res2 = new Upload().uploadFile(mResults3.get(2), getResources().getString(R.string.url) + "action=uploadImage_Menu3&id=" + id, "photo", Restaurant_Vendor.this);
                    Log.d("image Upload URl2", getResources().getString(R.string.url) + "?action=uploadImage_Menu3&id=" + id);
                    Log.d("image Upload Res2", res2);
                }

            } catch (Exception e) {
                return e.getMessage().toString();
            }
            return "valid";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Restaurant_Vendor.this, s, Toast.LENGTH_SHORT).show();
            if (s.equals("valid")) {
                finish();

            } else {
                Toast.makeText(Restaurant_Vendor.this, "Something goes wrong...Plz try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //declare second service herw

    class xyz extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "";
        String id = "";
       // String id = "";
        String id1 = "";


        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(Restaurant_Vendor.this);
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
                response = run(url + "action=Vendorrestaurant_details& Name=" + editresname.getText().toString() + "&re_id=" + reid + "&Res_Image=img2.png&Res_Image2=img2.png&Res_Image3=img2.png &Price=" + editavgcost.getText().toString() + "&Cusines=" + editcuisines.getText().toString() + "&Hours=" + hours.getText().toString() + "&Number=" + editphonenumber.getText().toString() + "&Information=" + multidescription.getText().toString() + "&Menu_Image=img4.png &Menu_Image1=img5.png &Menu_Image2=img6.png &Latitude" + latitude1.getText().toString() + "Longtitude=" + longtitude1.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");


                JSONObject obj = jarray.getJSONObject(0);
                res = obj.getString("status");
                id1 = obj.optString("id");

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
                Toast.makeText(Restaurant_Vendor.this, "Uploaded Seccesfull....", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(HotelVendor.this, Login.class);
                new UploadImage(id1).execute();
                new UploadImage1(id1).execute();
                //startActivity(i);

                //new xyz().execute();
                //  finish();

            } else {
                try {

                    //Intent i=new Intent(Register.this,Login.class);
                    //startActivity(i);


                    if (res.equals("valid")) {
                        Toast.makeText(Restaurant_Vendor.this, "added successfully!", Toast.LENGTH_SHORT).show();

                        // finish();
                    } else {
                        Toast.makeText(Restaurant_Vendor.this, "Try Again", Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    Toast.makeText(Restaurant_Vendor.this, "Some Error....", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class abc extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String res = "";


        @Override

        protected void onPreExecute() {
            try {
                progressDialog = new ProgressDialog(Restaurant_Vendor.this);
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
                response = run(url + "action=Vendor_restaurant&Name=" + editresname.getText().toString() + "&Image=img3.png&Address=" + "&Rating=" + rating1.getText().toString() + "&Address=" + res_address.getText().toString() + "&c_id=1");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jarray = jobj.getJSONArray("data");


                JSONObject obj = jarray.getJSONObject(0);
                res = obj.getString("status");
                reid = obj.getString("id");


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
                Toast.makeText(Restaurant_Vendor.this, "Some Error....", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


