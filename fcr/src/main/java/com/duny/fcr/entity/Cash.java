package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cash")
@Getter
@Setter
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="payment_id",unique = true,length = 10)
    private String paymentId;
    private double amount;
    private Date date = new Date();

}
