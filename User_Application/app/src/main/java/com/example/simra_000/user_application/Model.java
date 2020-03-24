package com.example.simra_000.user_application;

import java.io.Serializable;

/**
 * Created by simra_000 on 10-03-2018.
 */

public class Model implements Serializable {

    String name;
    String image;

    public Model(String name, String image) {
        this.name = name;
        this.image = image;
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

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }


}
