package com.example.simra_000.user_application;

/**
 * Created by soham on 6/24/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.simra_000.user_application.Hotel.List_Model;
import com.example.simra_000.user_application.Restaurant.Restaurant_Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

//import mt.com.yummy.yummydelivery.Model.Order_Cart.Order_Cart;

/**
 * Created by Hetal on 12/27/2017.
 */
public class arraylist_sharedpreference {

    public static final String CartPREFERENCES = "easy_trip_list";
    public static String Cart_Order = "whislist";
    public static String Restaurant = "res_whislist";
    public static Gson gson = new Gson();
    public static ArrayList<List_Model> hotels_list = new ArrayList<>();
    public static ArrayList<Restaurant_Model> restaurants_list = new ArrayList<>();



    public static ArrayList<List_Model> getHotel(Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        String empty_list = gson.toJson(new ArrayList<List_Model>());
        ArrayList<List_Model> mSelectedList = gson.fromJson(sharedpreferences.getString(Cart_Order, empty_list),
                new TypeToken<ArrayList<List_Model>>() {
                }.getType());

        Log.d("he33 mycart2111111", mSelectedList.toString());
        return mSelectedList;
    }

    public static void setHotel(Context c1,List_Model hotel_ArrayList) {
        hotels_list.clear();
        hotels_list=getHotel(c1);
        int flag=0;
        for (int i = 0; i < hotels_list.size(); i++) {
            if(hotels_list.get(i).toString().equals(hotel_ArrayList.toString())){
                flag=1;
            }

        }
        if(flag==0){
            hotels_list.add(hotel_ArrayList);
        }
        else {
            flag=0;
        }
        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart2", hotels_list.toString());
        String json = gson.toJson(hotels_list);
        editor.putString(Cart_Order, json);
        editor.apply();
        editor.commit();
    }




    public static ArrayList<Restaurant_Model> getRestaurant(Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        String empty1_list = gson.toJson(new ArrayList<Restaurant_Model>());
        ArrayList<Restaurant_Model> mSelectedList = gson.fromJson(sharedpreferences.getString(Restaurant, empty1_list),
                new TypeToken<ArrayList<Restaurant_Model>>() {
                }.getType());

        Log.d("he33 mycart2111111", mSelectedList.toString());
        return mSelectedList;
    }

    public static void setRestaurant(Context c1, Restaurant_Model restaurant_ArrayList) {
        restaurants_list.clear();
        restaurants_list=getRestaurant(c1);
        int flag=0;
        for (int i = 0; i < restaurants_list.size(); i++) {
            if(restaurants_list.get(i).toString().equals(restaurant_ArrayList.toString())){
                flag=1;
            }

        }
        if(flag==0){
            restaurants_list.add(restaurant_ArrayList);
        }
        else {
            flag=0;
        }

        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart2", restaurants_list.toString());
        String json = gson.toJson(restaurants_list);
        editor.putString(Restaurant, json);
        editor.apply();
        editor.commit();
    }


    /*public static  void SetCart_OrderList(Context c1,ArrayList<Hotel_Page_Model> order_cartArrayList){
        order_carts=getHotel(c1);
        for (int i = 0; i <order_cartArrayList.size() ; i++) {
            order_carts.add(order_cartArrayList.get(i));
        }
        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart56", order_carts.toString());
        String json = gson.toJson(order_carts);
        editor.putString(Cart_Order, json);
        editor.apply();
        editor.commit();

    }
*/
    public static ArrayList<List_Model> Update_Hotel(Context c1, ArrayList<List_Model> order_cartArrayList) {

        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart3", order_cartArrayList.toString());
        String json = gson.toJson(order_cartArrayList);
        editor.putString(Cart_Order, json);
        editor.apply();
        editor.commit();
        return order_cartArrayList;
    }

    public static ArrayList<Restaurant_Model> Update_Restaurant(Context c1, ArrayList<Restaurant_Model> Restaurant_List) {

        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart3", Restaurant_List.toString());
        String json = gson.toJson(Restaurant_List);
        editor.putString(Restaurant, json);
        editor.apply();
        editor.commit();
        return Restaurant_List;
    }

  /*  public static void clear_all_cart_per_vid(Context c1,String vid) {
        order_carts=getCart_Order(c1);
        ArrayList<Order_Cart> vdata=new ArrayList<>();


        for (int i = 0; i <order_carts.size() ; i++) {
            if(!order_carts.get(i).getV_id().equals(vid))
            {
                vdata.add(order_carts.get(i));
            }
        }

        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Log.d("he33 mycart5", vdata.toString());
        String json = gson.toJson(vdata);
        editor.putString(Cart_Order, json);
        editor.apply();
        editor.commit();

    }
*/
    public static void clearcart_item(String name, Context c1) {
        SharedPreferences sharedpreferences = c1.getSharedPreferences(CartPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(name);
        editor.commit();

    }

}
