package uk.co.advmob.newsapp;

import java.io.Serializable;

/**
 * Created by Kostas on 05/03/2018.
 */

public class Category implements Serializable {
    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
