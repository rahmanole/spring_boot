package com.duny.fcr.dto;

import java.util.Date;

public interface ITuitionFeeDueReport {
    String getName();
    String getStudentId();
    Date getDob();
    double getDue();
}
