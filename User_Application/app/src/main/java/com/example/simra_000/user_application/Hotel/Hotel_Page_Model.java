package com.example.simra_000.user_application.Hotel;

/**
 * Created by simra_000 on 29-03-2018.
 */

public class Hotel_Page_Model
{
    String id,name,hotel_description,hotel_image,hotel_image1,hotel_image2,amnities,price,hid,latitude,longtitde;


    public Hotel_Page_Model(String id,String name,String hotel_description, String hotel_image,String hotel_image1,String hotel_image2,String amnities,String price,String hid,String latitude,String longtitde)
    {
        this.id=id;
        this.name = name;
        this.hotel_description=hotel_description;
        this.hotel_image = hotel_image;
        this.hotel_image1=hotel_image1;
        this.hotel_image2=hotel_image2;
        this.amnities=amnities;
        this.price=price;
        this.hid = hid;
        this.latitude=latitude;
        this.longtitde=longtitde;

    }

    public  String getHotel_image1() {
        return hotel_image1;
    }

    public void setHotel_image1(String hotel_image1) {
        this.hotel_image1 = hotel_image1;
    }

    public  String getHotel_image2() {
        return hotel_image2;
    }

    public void setHotel_image2(String hotel_image2) {
        this.hotel_image2 = hotel_image2;
    }

    public String getHotel_description() {
        return hotel_description;
    }

    public void setHotel_description(String hotel_description) {
        this.hotel_description = hotel_description;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitde() {
        return longtitde;
    }

    public void setLongtitde(String longtitde) {
        this.longtitde = longtitde;
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

    public  String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }

    public String getAmnities() {
        return amnities;
    }

    public void setAmnities(String amnities) {
        this.amnities = amnities;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hotel_Page_Model{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hotel_description='" + hotel_description + '\'' +
                ", hotel_image='" + hotel_image + '\'' +
                ", hotel_image1='" + hotel_image1 + '\'' +
                ", hotel_image2='" + hotel_image2 + '\'' +
                ", amnities='" + amnities + '\'' +
                ", price='" + price + '\'' +
                ", hid='" + hid + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longtitde='" + longtitde + '\'' +
                '}';
    }
}
