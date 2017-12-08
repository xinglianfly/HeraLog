package com.example.xiner.background.entity;

import java.io.Serializable;

/**
 * Created by seald on 2017/12/7.
 */

public class Materials implements Serializable{
    private String _id;
    private   String type;
    private  String  name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "_id='" + _id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", count=" + count +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private  String size;
    private  int count;
}
