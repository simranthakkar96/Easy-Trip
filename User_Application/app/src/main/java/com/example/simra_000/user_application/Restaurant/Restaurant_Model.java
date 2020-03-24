package com.example.simra_000.user_application.Restaurant;

import java.io.Serializable;

/**
 * Created by simra_000 on 04-03-2018.
 */

public class Restaurant_Model implements Serializable
{
    String id,c_id,name,place,res_image,rating;


    public Restaurant_Model(String id,String c_id, String name,String place,String res_image,String rating)
    {
        this.id=id;
        this.c_id=c_id;
        this.name = name;
        this.res_image = res_image;
        this.rating=rating;
        this.place=place;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRes_image() {
        return res_image;
    }

    public void setRes_image(String res_image) {
        this.res_image = res_image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant_Model{" +
                "id='" + id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", res_image='" + res_image + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
