package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "opening_due_payment")
public class OpeningDuePayment {

//    insert into  admission_payment(admission_fee,admission_fee_due,admission_fee_paid,af_payment_id,month,student_id) values(0,0,0,'AF1','month','100');
//    insert into tuition_fee_payment(month,student_id,tf_payment_id,tuition_fee,tuition_fee_due,tuition_fee_paid) values('month','100','TF',0,0,0);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10,nullable = false)
    private String odfFPaymentId;
    @Column(length = 10,nullable = false)
    private String studentId;
    @Column(length = 10,nullable = false)
    private String month;
    private double remainingODFFee;
    private double ODFFeePaid;
    private double ODFFeeDue;
}
