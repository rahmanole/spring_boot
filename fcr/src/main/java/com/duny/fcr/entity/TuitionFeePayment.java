package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tuition_fee_payment")
public class TuitionFeePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10,nullable = false)
    private String tfPaymentId;
    @Column(length = 10,nullable = false)
    private String studentId;
    @Column(length = 10,nullable = false)
    private String month;
    private double tuitionFee;
    private double tuitionFeePaid;
    private double tuitionFeeDue;
    @Column(length = 5)
    private String year;


}
