package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "money_order")
@Getter
@Setter
public class MoneyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date = LocalDate.now();
    @Column(name = "payment_id",length = 10)
    private String paymentId;
    private String studentId;
    private Date moneyOrderDate;
    @Column(length = 30,nullable = true)
    private String moneyOrderNum;
    private double amount;
    @Column(length = 10)
    private String year;
    private String month;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String moneyOrderImg;

}
