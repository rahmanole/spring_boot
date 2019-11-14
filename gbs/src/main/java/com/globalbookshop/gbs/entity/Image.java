package com.globalbookshop.gbs.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "img_url", columnDefinition = "TEXT",nullable = false)
    private String imgUrl;

    @Column(name="img_type", length = 20,nullable = false)
    @ColumnDefault("book")
    private String imageTyoe;

    @Column(length = 50)
    private String title;

    @Column(length = 100)
    private String subtitle;


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

    public String getImageTyoe() {
        return imageTyoe;
    }

    public void setImageTyoe(String imageTyoe) {
        this.imageTyoe = imageTyoe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
