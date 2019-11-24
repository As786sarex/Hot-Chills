package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 21-09-2019 at 17:29
*/


import java.util.List;

public class Order {
    private String orderName;
    private int tableNo;
    private String uid;
    private int amount;
    private long timestamp;
    private String orderDate;
    private List<String> itemNames;

    public Order(String orderName, int tableNo, String uid, int amount, long timestamp, String orderDate, List<String> itemNames) {
        this.orderName = orderName;
        this.timestamp = timestamp;
        this.tableNo = tableNo;
        this.uid = uid;
        this.amount = amount;
        this.orderDate = orderDate;
        this.itemNames = itemNames;
    }

    public Order() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }
}
