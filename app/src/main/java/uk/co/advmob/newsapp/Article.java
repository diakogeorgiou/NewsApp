package uk.co.advmob.newsapp;

import java.io.Serializable;

/**
 * Created by User on 28/01/2018.
 */

public class Article implements Serializable {
    private int id;
    private String title;
    private String date;
    private String image;
    private String content;
    private Journalist author;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Journalist getAuthor() {
        return author;
    }

    public void setAuthor(Journalist author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
