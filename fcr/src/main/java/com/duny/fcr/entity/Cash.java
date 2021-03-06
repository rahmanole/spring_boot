package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cash")
@Getter
@Setter
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="payment_id",length = 10)
    private String paymentId;
    private String studentId;
    @Column(length = 10)
    private String year;
    private String month;
    private double amount;
    private LocalDate date = LocalDate.now();

}
