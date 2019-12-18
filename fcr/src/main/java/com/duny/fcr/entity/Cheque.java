package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cheque")
@Getter
@Setter
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date = new Date();
    private Date chequeDate;
    @Column(length = 30,nullable = true)
    private String accountNum;
    @Column(length = 30,nullable = true)
    private String chequeNum;
    private double amount;
    @Column(columnDefinition = "blob")
    private byte[] chequeImg;
    @Column(name = "payment_id",length = 10,unique = true)
    private String paymentId;


}
