package com.example.xiner.background.entity;

import java.io.Serializable;

/**
 * Created by seald on 2017/12/10.
 */

public class Fee implements Serializable {
    public double getSort() {
        return sort;
    }

    public void setSort(double sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "sort=" + sort +
                ", car=" + car +
                ", other1=" + other1 +
                ", other2=" + other2 +
                '}';
    }

    public double getCar() {
        return car;
    }

    public void setCar(double car) {
        this.car = car;
    }

    public double getOther1() {
        return other1;
    }

    public void setOther1(double other1) {
        this.other1 = other1;
    }

    public double getOther2() {
        return other2;
    }

    public void setOther2(double other2) {
        this.other2 = other2;
    }

    double sort;
    double car;
    double other1;
    double other2;
}
