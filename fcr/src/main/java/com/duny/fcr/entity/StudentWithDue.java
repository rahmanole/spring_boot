package com.duny.fcr.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentWithDue {
    private String studentID;
    private String studentName;
    private String dob;
    private String fatherName;
    private double totalDue;

}
