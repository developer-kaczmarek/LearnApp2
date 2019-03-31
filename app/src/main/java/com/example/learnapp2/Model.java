package com.example.learnapp2;

import java.io.Serializable;

public class Model implements Serializable {
    private int image;
    private String author;
    private String desc;
    private String tags;


    public Model(int image, String author, String desc, String tags) {
        this.image = image;
        this.author = author;
        this.desc = desc;
        this.tags = tags;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
