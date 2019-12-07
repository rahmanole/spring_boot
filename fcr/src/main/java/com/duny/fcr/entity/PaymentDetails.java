package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String st_id;
    private Date date = new Date();
    private double cash;
    private double cheque;
    private double creditCard;
    private double bank;
    private double paymentType;
    private double monthlyFee;
    private double monthlyFeePaid;
    private double monthlyFeeDue;
    private double collectionTarget;
    private double collected;
    private double collectionDue;

}
