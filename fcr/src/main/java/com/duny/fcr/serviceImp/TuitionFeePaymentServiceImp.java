package com.duny.fcr.serviceImp;

import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.service.TuitionFeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TuitionFeePaymentServiceImp implements TuitionFeePaymentService {
    @Autowired
    TuitionFeePaymentRepo tuitionFeePaymentRepo;

    @Override
    public String getTuitionFeePaymentId() {
        String id = tuitionFeePaymentRepo.getMaxId()+"";
        return (id != null?(Long.parseLong(id)+1+""):(0+""));
    }
}
