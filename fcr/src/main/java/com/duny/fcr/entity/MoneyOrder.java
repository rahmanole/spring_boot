package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cheque")
@Getter
@Setter
public class MoneyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    private Date moneyOrderDate;
    @Column(length = 30,nullable = true)
    private String moneyOrderNum;
    private double amount;
    private byte[] moneyOrderImg;

}
