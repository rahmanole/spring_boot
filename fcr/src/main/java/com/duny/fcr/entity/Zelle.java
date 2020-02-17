package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "zelle")
@Getter
@Setter
public class Zelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date = LocalDate.now();
    @Column(name="payment_id",length = 10)
    private String paymentId;
    private String studentId;
    private double amount;
    @Column(length = 15,nullable = true)
    private String phoneNum;
    @Column(length = 30,nullable = true)
    private String email;
    @Column(length = 10)
    private String year;
    private String month;
}
