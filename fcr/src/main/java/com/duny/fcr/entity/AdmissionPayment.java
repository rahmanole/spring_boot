package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "admission_payment")
public class AdmissionPayment {

//    insert into  admission_payment(admission_fee,admission_fee_due,admission_fee_paid,af_payment_id,month,student_id) values(0,0,0,'AF1','month','100');
//    insert into tuition_fee_payment(month,student_id,tf_payment_id,tuition_fee,tuition_fee_due,tuition_fee_paid) values('month','100','TF',0,0,0);
//    insert into opening_due_payment(odffee_due,odffee_paid,month,odffpayment_id,remainingodffee,student_id) values(0,0,'month','ODF0',0,'000');

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10,nullable = false)
    private String afPaymentId;
    @Column(length = 10,nullable = false)
    private String studentId;
    @Column(length = 10,nullable = false)
    private String year;
    private double admissionFee;
    private double admissionFeePaid;
    private double admissionFeeDue;

}
