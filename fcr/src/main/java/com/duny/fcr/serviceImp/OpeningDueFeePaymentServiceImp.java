package com.duny.fcr.serviceImp;

import com.duny.fcr.repo.OpeningDueFeePaymentRepo;
import com.duny.fcr.service.OpeningDueFeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OpeningDueFeePaymentServiceImp implements OpeningDueFeePaymentService {
    @Autowired
    OpeningDueFeePaymentRepo openingDueFeePaymentRepo;

    @Override
    public String getOpeningDueFeePaymentId() {
        return "ODF"+(openingDueFeePaymentRepo.getMaxId()+1);
    }
}
