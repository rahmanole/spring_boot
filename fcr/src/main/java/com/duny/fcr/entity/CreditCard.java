package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "credit_card")
@Getter
@Setter
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date = LocalDate.now();
    @Column(length = 30,nullable = true)
    private String cardNum;
    @Column(length = 30,nullable = true)
    private String tnxId;
    private double amount;
    @Column(name="payment_id",length = 10)
    private String paymentId;
    private String studentId;
    @Column(length = 10)
    private String year;
    private String month;
}
