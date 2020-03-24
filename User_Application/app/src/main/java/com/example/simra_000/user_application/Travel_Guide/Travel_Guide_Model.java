package com.example.simra_000.user_application.Travel_Guide;

import android.widget.Button;

/**
 * Created by simra_000 on 01-04-2018.
 */

public class Travel_Guide_Model
{
    String id,c_id,agency,address,guide_name,guide_contact;


    public Travel_Guide_Model(String id, String agency, String c_id,String address, String guide_name, String guide_contact) {
        this.id = id;
        this.agency = agency;
        this.c_id=c_id;
        this.address = address;
        this.guide_name = guide_name;
        this.guide_contact = guide_contact;
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

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuide_name() {
        return guide_name;
    }

    public void setGuide_name(String guide_name) {
        this.guide_name = guide_name;
    }

    public String getGuide_contact() {
        return guide_contact;
    }

    public void setGuide_contact(String guide_contact) {
        this.guide_contact = guide_contact;
    }

    @Override
    public String toString() {
        return "Travel_Guide_Model{" +
                "id='" + id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", agency='" + agency + '\'' +
                ", address='" + address + '\'' +
                ", guide_name='" + guide_name + '\'' +
                ", guide_contact='" + guide_contact + '\'' +
                '}';
    }
}
