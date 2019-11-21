package com.heartsun.informer;

/**
 * Created by Bijay on 11/19/2019.
 */

public class Modelclass {
    String id;
    int image;
    String title,message;

    public Modelclass() {

    }

    public Modelclass(String id,int image, String title, String message) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
