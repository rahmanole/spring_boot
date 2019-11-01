package com.globalbookshop.gbs.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "img_url", columnDefinition = "TEXT")
    private String imgUrl;
}
