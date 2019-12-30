package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zelle")
@Getter
@Setter
public class Zelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    @Column(name="payment_id",unique = true,length = 10)
    private String paymentId;
    private double amount;
    @Column(length = 15,nullable = true)
    private String phoneNum;
    @Column(length = 30,nullable = true)
    private String email;

}
