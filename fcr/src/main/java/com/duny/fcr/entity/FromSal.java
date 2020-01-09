package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "from_sal")
@Getter
@Setter
public class FromSal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    private Date payPeriod;
    @Column(length = 30,nullable = true)
    private String chequeNum;
    private double amount;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] fromSalChequeImg;
    @Column(name = "payment_id",length = 10,unique = true)
    private String paymentId;
}