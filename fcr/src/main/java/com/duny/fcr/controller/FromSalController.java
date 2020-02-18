package com.duny.fcr.controller;

import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.FromSal;
import com.duny.fcr.repo.AdmissionPaymentRepo;
import com.duny.fcr.repo.ChequeRepo;
import com.duny.fcr.repo.FromSalRepo;
import com.duny.fcr.repo.TuitionFeePaymentRepo;
import com.duny.fcr.serviceImp.FeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Controller
public class FromSalController {

    @Autowired
    FromSalRepo fromSalRepo;
    @Autowired
    private FeeServiceImp feeService;
    @Autowired
    private TuitionFeePaymentRepo tuitionFeePaymentRepo;
    @Autowired
    private AdmissionPaymentRepo admissionPaymentRepo;

    @PostMapping("/fromSal/save")
    public String saveCheque(@RequestParam("paymentId")String pid,
                             @RequestParam("studentId") String stId,
                             @RequestParam("chequeNum") String cNum,
                             @RequestParam("payPeriod") String payPeriod,
                             @RequestParam("amount") String amount,
                             @RequestParam("fromSalChequeImg") MultipartFile file) throws Exception {
        if(file.isEmpty()){
            return "";
        }

        FromSal fromSal = new FromSal();
        fromSal.setPaymentId(pid);
        fromSal.setStudentId(stId);
        fromSal.setChequeNum(cNum);
        fromSal.setAmount(Double.parseDouble(amount));
        fromSal.setFromSalChequeImg("data:image/jpg;base64,"+Base64.getEncoder().encodeToString(file.getBytes()));
        fromSal.setDate(LocalDate.parse(payPeriod));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        fromSal.setYear(calendar.get(Calendar.YEAR)+"");
        fromSal.setMonth(LocalDate.now().getMonth().toString());
        fromSalRepo.save(fromSal);

        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/fromSal/delete/{id}")
    public String deleteCash(@PathVariable Long id){
        FromSal fromSal = fromSalRepo.getOne(id);
        double deletedAmount = fromSal.getAmount();
        String pid = fromSal.getPaymentId();

        String url="redirect:/tf/details/"+pid;

        fromSalRepo.deleteById(id);

        feeService.updateTuitionOrAdmissionFee(pid,deletedAmount,tuitionFeePaymentRepo,admissionPaymentRepo);

        return url;
    }
}
