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
    @Column(name = "student_id",length = 10)
    private int studentId;
    @Column(name = "application_id",length = 10)
    private int applicationId;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30,nullable = false)
    private String email;
    @Column(name="parent_email",length = 30,nullable = false)
    private String parentEmail;
    @Column(length = 6,nullable = false)
    private String gender;
    @Column(name="course_name")
    private String courseName;
    @Column(length = 5,nullable = false)
    private String boarding;
    private Date dob;
    @Column(length = 30,nullable = false)
    private String motherName;
    @Column(length = 15,nullable = false)
    private String motherCell;
    @Column(length = 30,nullable = false)
    private String fatherName;
    @Column(length = 15,nullable = false)
    private String fatherCell;
    @Column(nullable = false)
    private String address;
    @Column(length = 10,nullable = false)
    private String aptNo;
    @Column(length = 30,nullable = false)
    private String city;
    @Column(length = 30,nullable = false,columnDefinition = "varchar(30) default 'applied'")
    private String state = "applied";
    @Column(length = 10,nullable = false)
    private String zip;
    @Column(length = 50,nullable = false)
    private String currentSchool;
    @Column(length = 30,nullable = false)
    private String currentGrade;
    @Column(nullable = false, columnDefinition = "varchar(30) default 'nd'" )
    private String feePolicy="nd";
    private Date dateOfAdmission;
    @Column(length = 15,nullable = false)
    private String status;
    @OneToOne(mappedBy = "student",fetch = FetchType.LAZY)
    private FinDtlsOfStudent finDtlsOfStudent;
}
