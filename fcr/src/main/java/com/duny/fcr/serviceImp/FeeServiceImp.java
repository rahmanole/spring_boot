package com.duny.fcr.serviceImp;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.*;
import com.duny.fcr.service.FeeService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeServiceImp implements FeeService {
    @Autowired
    CashRepo cashRepo;
    @Autowired
    ChequeRepo chequeRepo;
    @Autowired
    CreditCardRepo creditCardRepo;
    @Autowired
    MoneyOrderRepo moneyOrderRepo;
    @Autowired
    FromSalRepo fromSalRepo;
    @Autowired
    ZelleReo zelleReo;

    @Override
    public double getTotalFeePaid(String pid) {
        double totalAmount;

        Object amount = cashRepo.getAmount(pid);
        double cash = (amount != null? Double.parseDouble(amount.toString()):0);
        System.out.println(amount);
        amount = chequeRepo.getAmount(pid);
        double cheque = (amount != null? Double.parseDouble(amount.toString()):0);

        amount = creditCardRepo.getAmount(pid);
        double cc = (amount != null? Double.parseDouble(amount.toString()):0);

        amount = fromSalRepo.getAmount(pid);
        double fromSal = (amount !=null? Double.parseDouble(amount.toString()):0);

        amount = moneyOrderRepo.getAmount(pid);
        double mo = (amount !=null? Double.parseDouble(amount.toString()):0);

        amount = zelleReo.getAmount(pid);
        double zelle = ( amount !=null? Double.parseDouble(amount.toString()):0);

        totalAmount = cash+cheque+cc+fromSal+mo+zelle;

        return totalAmount;
    }

    @Override
    public void updateTuitionOrAdmissionFee(String pid,double deletedAmount,TuitionFeePaymentRepo tuitionFeePaymentRepo,AdmissionPaymentRepo admissionPaymentRepo) {
        if(pid.contains("TF")){
            TuitionFeePayment tf = tuitionFeePaymentRepo.findTuitionFeePaymentByTfPaymentId(pid);
            System.out.println(deletedAmount);
            tf.setTuitionFeePaid((tf.getTuitionFeePaid()-deletedAmount));
            tf.setTuitionFeeDue((tf.getTuitionFeeDue()+deletedAmount));
            tuitionFeePaymentRepo.save(tf);
        }else {
            AdmissionPayment af = admissionPaymentRepo.findAdmissionPaymentByAfPaymentId(pid);
            af.setAdmissionFeePaid((af.getAdmissionFeePaid()-deletedAmount));
            af.setAdmissionFeeDue((af.getAdmissionFeeDue()+deletedAmount));
            admissionPaymentRepo.save(af);
        }
    }
}
