package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cash")
@Getter
@Setter
public class Zelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    private double amount;
    @Column(length = 15,nullable = true)
    private String phoneNum;
    @Column(length = 30,nullable = true)
    private String email;

}
