package com.example.simra_000.vendor_application;

/**
 * Created by simra_000 on 26-03-2018.
 */

import android.content.Context;
import android.content.SharedPreferences;


public class sharedpreference {

    public static final String MyPREFERENCES = "CityServices" ;
    public static String user_id="userid";
    public static String username="username";
    public static String city_id="city_id";


    public static String getUser_id(Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String ans =sharedpreferences.getString(user_id,"no");
        return  ans;
    }
    public static void setUser_id(Context c1,String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(user_id, value);
        editor.apply();
    }

    public static String getCity_id(Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String ans =sharedpreferences.getString(city_id,"no");
        return  ans;
    }
    public static void setCity_id(Context c1,String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(city_id, value);
        editor.apply();
    }

    public static String getUsername(Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String ans =sharedpreferences.getString(username,"no");
        return  ans;
    }
    public static void setUsername(Context c1,String value) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(username, value);
        editor.apply();
    }


    public static void clearAll(Context c1)
    {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
    public static void clearValue(Context c1,String name) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(name);
        editor.apply();
    }

}

