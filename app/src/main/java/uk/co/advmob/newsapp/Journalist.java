package uk.co.advmob.newsapp;

import java.io.Serializable;

public class Journalist extends User implements Serializable {
    public Journalist(int id, String username, String password, String email, String fullName, String profilePicture) {
        super(id, username, password, email, fullName, profilePicture);
    }

    public Journalist() {
    }

    public void saveArticle(Article article) {

    }

    public void updateArticle(Article article) {

    }

    public void deleteArticle(Article article) {

    }
}
