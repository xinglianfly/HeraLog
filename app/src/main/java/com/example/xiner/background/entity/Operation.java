package com.example.xiner.background.entity;

import java.io.Serializable;

/**
 * Created by seald on 2017/12/7.
 */

public class Operation implements Serializable{
    private String _id;
    private String level;
    private String timestamp;
    private Report report;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "_id='" + _id + '\'' +
                ", level='" + level + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", report=" + report +
                ", __v='" + __v + '\'' +
                '}';
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    private String __v;
}
