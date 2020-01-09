package com.duny.fcr.dto;

import java.util.Date;

public interface TuitionFeeDueReport {
    String getName();
    String getStudentId();
    Date getDob();
    double getDue();
}
