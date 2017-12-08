package com.example.xiner.background.entity;



import java.io.Serializable;
import java.util.List;

/**
 * Created by seald on 2017/12/7.
 */

public class DataRes implements Serializable{
    @Override
    public String toString() {
        return "DataRes{" +
                "operations=" + operations +
                '}';
    }

    private List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
