package com.globalbookshop.gbs.entity;

import javax.persistence.*;

@Entity
@Table(name = "slider_images")
public class SliderImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "img_url", columnDefinition = "MEDIUMTEXT",length = 500000,nullable = false)
    private String imgUrl;

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
