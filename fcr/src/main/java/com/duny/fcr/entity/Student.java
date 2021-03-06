package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "student_id",length =10,unique = true)
    private String studentId;
    @Column(name = "application_id",length = 10,unique = true,nullable = false)
    private int applicationId;
    @Column(nullable = false)
    private String name;
    @Column(name="parent_email",nullable = false)
    private String parentEmail;
    @Column(nullable = false)
    private String gender;
    @Column(name="course_name")
    private String courseName;
    @Column(name="year",length = 20)
    private String year="NA";
    @Column(nullable = false)
    private String boarding = "no";
    private LocalDate dob;
    @Column(nullable = true)
    private String homePhone;
    @Column(length = 30,nullable = false)
    private String motherName;
    @Column(length = 15,nullable = true)
    private String motherCell;
    @Column(length = 30,nullable = false)
    private String fatherName;
    @Column(length = 15,nullable = true)
    private String fatherCell;
    @Column(length = 15,nullable = true)
    private String emgCnt;
    @Column(nullable = false)
    private String address;
    @Column(nullable = true)
    private String aptNo;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false)
    private String currentSchool;
    @Column(nullable = false)
    private String currentGrade;

    @Column(nullable = true)
    private double initialDue = 0;
    private LocalDate dateOfAdmission;
    @Column(nullable = false)
    private String status  = "applied";
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="fin_details_id")
    private FinDtlsOfStudent finDtlsOfStudent;
}
