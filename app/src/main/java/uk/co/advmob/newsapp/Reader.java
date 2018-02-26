package uk.co.advmob.newsapp;

import java.util.Date;

public class Reader extends User {
    private Date expireDate;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void renewSubscription() {

    }
}
