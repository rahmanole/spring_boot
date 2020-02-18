package com.duny.fcr.service;

import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import org.springframework.stereotype.Service;

@Service
public interface FeeService {
    double getTotalFeePaid(String pid);

    void updateTuitionOrAdmissionFee(String pid, double deletedAmount, TuitionFeePaymentRepo tuitionFeePaymentRepo, AdmissionPaymentRepo admissionPaymentRepo) ;
}
