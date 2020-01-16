package com.duny.fcr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TuitionFeeDueReport {
    private String name;
    private String stID;
    private Date dob;
    private double due;
}
