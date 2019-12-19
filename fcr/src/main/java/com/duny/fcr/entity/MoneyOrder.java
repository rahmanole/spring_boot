package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "money_order")
@Getter
@Setter
public class MoneyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    @Column(name = "payment_id",length = 10,unique = true)
    private String paymentID;
    private Date moneyOrderDate;
    @Column(length = 30,nullable = true)
    private String moneyOrderNum;
    private double amount;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] moneyOrderImg;

}
