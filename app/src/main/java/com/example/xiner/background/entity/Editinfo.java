package com.example.xiner.background.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by seald on 2017/12/7.
 */

public class Editinfo  implements Serializable{
    private List<String>path;

    public Object getOld() {
        return old;
    }

    public void setOld(Object old) {
        this.old = old;
    }

    private Object old;

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }




    @Override
    public String toString() {
        return "Editinfo{" +
                "path=" + path +
                ", old='" + old + '\'' +
                ", New=" + New +
                '}';
    }

    public Object getNew() {
        return New;
    }

    public void setNew(Object aNew) {
        New = aNew;
    }

    @SerializedName(value="New", alternate={"new"})
    private Object New;//本应该是new字段

}
