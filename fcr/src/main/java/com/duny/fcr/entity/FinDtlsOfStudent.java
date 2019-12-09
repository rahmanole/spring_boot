package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fin_details")
@Getter
@Setter
public class FinDtlsOfStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // One student can take maximum 1 sponsors
    // or one sponsor take take 1 students
    @Column(nullable = true)
    private long sp_id;
    // One student can take maximum three dadd
    @Column(nullable = true)
    private boolean hasDadd;

    @Column(nullable = true)
    private double collection;
    @Column(nullable = true)
    private int sibling_num;
    @Column(nullable = true)
    private String sibling_ids;
    @Column(nullable = true)
    private boolean isStaffChild;
    @Column(nullable = true)
    private double zakat;
    @Column(nullable = true)
    private boolean isSelfFunded;

    @Column(name="mand_fees",nullable = true)
    private double mandatoryFees;
    @Column(name="mand_fees_paid",nullable = true)
    private double mandatoryFeesPaid;
}
