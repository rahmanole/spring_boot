package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="sponsor")
@Getter
@Setter
public class Sponsor {

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
    @Column(name = "donation_period")
    private String donationInterval;
    @Column(name = "donation_interval")
    private double donationAmount;

}
