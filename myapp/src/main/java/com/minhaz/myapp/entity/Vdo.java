package com.minhaz.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "vdo")
public class Vdo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vdoUrl;
    private String vdoCaption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVdoUrl() {
        return vdoUrl;
    }

    public void setVdoUrl(String vdoUrl) {
        this.vdoUrl = vdoUrl;
    }

    public String getVdoCaption() {
        return vdoCaption;
    }

    public void setVdoCaption(String vdoCaption) {
        this.vdoCaption = vdoCaption;
    }
}
