package com.example.xiner.background.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by seald on 2017/12/7.
 */

public class EditMaterial implements Serializable {
    private Materials old;
    @SerializedName(value = "New",alternate = ("new"))
    private Materials New;

    public Materials getOld() {
        return old;
    }

    @Override
    public String toString() {
        return "EditMaterial{" +
                "old=" + old +
                ", New=" + New +
                '}';
    }

    public void setOld(Materials old) {
        this.old = old;
    }

    public Materials getNew() {
        return New;
    }

    public void setNew(Materials aNew) {
        New = aNew;
    }
}
