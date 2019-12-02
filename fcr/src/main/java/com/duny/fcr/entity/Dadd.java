package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="dadd")
@Getter
@Setter
public class Dadd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30,nullable = false,unique = true)
    private String email;
    @Column(length = 15)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(name="donation_amount")
    private double donationAmount = 1.0;
    @Column(length = 6,nullable = true)
    private int st_id;

}
