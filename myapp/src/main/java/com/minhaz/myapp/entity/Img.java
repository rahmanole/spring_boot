package com.minhaz.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name="img")
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imgUrl;
    @Column(length = 10000)
    private String imgCaption;

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
}
