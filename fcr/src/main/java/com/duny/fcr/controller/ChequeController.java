package com.duny.fcr.controller;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.ChequeRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.serviceImp.FeeServiceImp;
import com.duny.fcr.serviceImp.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ChequeController {

    @Autowired
    ChequeRepo chequeRepo;
    @Autowired
    private FeeServiceImp feeService;
    @Autowired
    private TuitionFeePaymentRepo tuitionFeePaymentRepo;
    @Autowired
    private AdmissionPaymentRepo admissionPaymentRepo;

    @PostMapping("/cheque/save")
    public String saveCheque(@RequestParam("paymentId")String pid,
                             @RequestParam("studentId") String stId,
                             @RequestParam("accountNum") String acNum,
                             @RequestParam("chequeNum") String cNum,
                             @RequestParam("chequeDate") String cDate,
                             @RequestParam("amount") String amnt,
                             @RequestParam("chequeImg") MultipartFile file) throws Exception {
        if(file.isEmpty()){

            return "";
        }
        Cheque cheque = new Cheque();
        cheque.setPaymentId(pid);
        cheque.setStudentId(stId);
        cheque.setAccountNum(acNum);
        cheque.setAccountNum(cNum);
        cheque.setAmount(Double.parseDouble(amnt));
        cheque.setChequeImg("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(file.getBytes()));
        cheque.setChequeDate(new SimpleDateFormat("yyyy-mm-dd").parse(cDate));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        cheque.setMonth(LocalDate.now().getMonth().toString());
        cheque.setYear(calendar.get(Calendar.YEAR)+"");
        chequeRepo.save(cheque);

//        System.out.println(pid+acNum+cNum+cDate+amnt);
//        System.out.println(Base64.getEncoder().encodeToString(file.getBytes()));

        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/cheque/delete/{id}")
    public String deleteCash(@PathVariable Long id){
        Cheque cheque = chequeRepo.getOne(id);
        double deletedAmount = cheque.getAmount();
        String pid = cheque.getPaymentId();

        String url="redirect:/tf/details/"+pid;

        chequeRepo.deleteById(id);

        feeService.updateTuitionOrAdmissionFee(pid,deletedAmount,tuitionFeePaymentRepo,admissionPaymentRepo);

        return url;
    }
}
