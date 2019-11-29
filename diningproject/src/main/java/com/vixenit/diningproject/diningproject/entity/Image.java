package com.vixenit.diningproject.diningproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "img_url", columnDefinition = "MEDIUMTEXT",length = 500000,nullable = false)
    private String imgUrl;
    @Column(length = 10)
    private String imgName="boarding";
}
