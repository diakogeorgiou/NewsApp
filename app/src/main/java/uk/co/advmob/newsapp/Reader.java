package uk.co.advmob.newsapp;

import java.io.Serializable;
import java.util.Date;

public class Reader extends User implements Serializable {
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
