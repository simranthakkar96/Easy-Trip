package com.example.simra_000.user_application.Hotel;

import java.io.Serializable;

/**
 * Created by simra_000 on 03-03-2018.
 */

public class List_Model implements Serializable
{
    String name,image,rating,place,id,c_id;


    public List_Model(String id,String c_id,String name, String image,String rating,String place)
    {
        this.id=id;
        this.c_id=c_id;
        this.name = name;
        this.image = image;
        this.rating=rating;
        this.place=place;

    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "List_Model{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating + '\'' +
                ", place='" + place + '\'' +
                ", id='" + id + '\'' +
                ", c_id='" + c_id + '\'' +
                '}';
    }
}
