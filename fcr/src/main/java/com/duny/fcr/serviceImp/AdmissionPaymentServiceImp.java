package com.duny.fcr.serviceImp;

import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.service.AdmissionPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdmissionPaymentServiceImp implements AdmissionPaymentService {
    @Autowired
    AdmissionPaymentRepo admissionPaymentRepo;

    @Override
    public String getAdmissionPaymentId() {
        return "ADM"+(admissionPaymentRepo.getMaxId()+1);
    }
}
