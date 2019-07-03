package com.minhaz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="para")
public class Para {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imgUrl;
    private String imgCaption;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgCaption() {
        return imgCaption;
    }

    public void setImgCaption(String imgCaption) {
        this.imgCaption = imgCaption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
