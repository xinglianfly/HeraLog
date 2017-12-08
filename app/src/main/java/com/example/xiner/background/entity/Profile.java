package com.example.xiner.background.entity;

import java.io.Serializable;

/**
 * Created by seald on 2017/12/7.
 */

public class Profile  implements Serializable{
    private String name;

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
