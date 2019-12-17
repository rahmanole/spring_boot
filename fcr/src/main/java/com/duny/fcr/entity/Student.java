package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30)
    private String email;
    @Column(name="parent_email",length = 30,nullable = false)
    private String parentEmail;
    @Column(length = 6,nullable = false)
    private String gender;
    @Column(name="course_name",length = 20)
    private String courseName;
    @Column(length = 5,nullable = false)
    private String boarding;
    private Date dob;
    @Column(length = 15,nullable = true)
    private String homePhone;
    @Column(length = 30,nullable = false)
    private String motherName;
    @Column(length = 15,nullable = true)
    private String motherCell;
    @Column(length = 30,nullable = false)
    private String fatherName;
    @Column(length = 15,nullable = true)
    private String fatherCell;
    @Column(nullable = false)
    private String address;
    @Column(length = 10,nullable = true)
    private String aptNo;
    @Column(length = 30,nullable = false)
    private String city;
    @Column(length = 30,nullable = false)
    private String state;
    @Column(length = 10,nullable = false)
    private String zip;
    @Column(length = 50,nullable = false)
    private String currentSchool;
    @Column(nullable = false)
    private String currentGrade;
    private Date dateOfAdmission;
    @Column(length = 15,nullable = false)
    private String status  = "applied";
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="fin_details_id")

    private FinDtlsOfStudent finDtlsOfStudent;

}
