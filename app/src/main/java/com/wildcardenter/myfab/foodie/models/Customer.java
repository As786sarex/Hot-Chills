package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 21-09-2019 at 17:04
*/


import java.util.List;

public class Customer extends User {

    List<CardInfo> cardInfo;
    List<String> orderIds;
    public Customer(String name, String email, String uid, int acCode,List<CardInfo> cardInfo,List<String> orderId) {
        super(name, email, uid, acCode);
        this.cardInfo=cardInfo;
        this.orderIds=orderId;
    }

    public List<CardInfo> getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(List<CardInfo> cardInfo) {
        this.cardInfo = cardInfo;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }
}
