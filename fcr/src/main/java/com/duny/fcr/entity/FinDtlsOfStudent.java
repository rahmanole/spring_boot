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
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="st_id",nullable = false,unique = true)
    private Student student;
    // One student can take maximum two sponsors
    // or one sponsor take take two students
    @Column(nullable = true)
    private long sp_id;
    // One student can take maximum three dadd
    @Column(nullable = true)
    private long dadd1_id;
    @Column(nullable = true)
    private double dadd1_alloc;
    @Column(nullable = true)
    private long dadd2_id;
    @Column(nullable = true)
    private double dadd2_alloc;
    @Column(nullable = true)
    private long dadd3_id;
    @Column(nullable = true)
    private double dadd3_alloc;

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
}
