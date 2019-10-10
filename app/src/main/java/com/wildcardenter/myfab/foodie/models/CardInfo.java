package com.wildcardenter.myfab.foodie.models;

/*
    Class On Package com.wildcardenter.myfab.foodie.models
    
    Created by Asif Mondal on 21-09-2019 at 17:08
*/


public class CardInfo {
    private String cardNo;
    private String expirationDate;
    private int cvv;
    private String cardName;

    public CardInfo(String cardNo, String expirationDate, int cvv, String cardName) {
        this.cardNo = cardNo;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.cardName = cardName;
    }

    public CardInfo() {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
