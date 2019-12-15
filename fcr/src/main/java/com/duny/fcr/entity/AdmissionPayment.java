package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "admission_payment")
public class AdmissionPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10,nullable = false)
    private String afPaymentId;
    @Column(length = 10,nullable = false)
    private String studentId;
    @Column(length = 10,nullable = false)
    private String month;
    private double admissionFee;
    private double admissionFeePaid;
    private double admissionFeeDue;

}
