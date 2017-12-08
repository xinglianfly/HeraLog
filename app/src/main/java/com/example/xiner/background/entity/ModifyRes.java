package com.example.xiner.background.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by seald on 2017/12/7.
 */

public class ModifyRes implements Serializable{
    private String message;
    private DataRes data;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ModifyRes{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataRes getData() {
        return data;
    }

    public void setData(DataRes data) {
        this.data = data;
    }
}
