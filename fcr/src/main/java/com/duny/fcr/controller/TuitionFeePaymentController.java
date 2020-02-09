package com.duny.fcr.controller;

import com.duny.fcr.dto.ITuitionFeeDueReport;
import com.duny.fcr.dto.TuitionFeeDueReport;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.*;
import com.duny.fcr.service.TuitionFeePaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TuitionFeePaymentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TuitionFeePaymentService tuitionFeePaymentService;
    @Autowired
    TuitionFeePaymentRepo tuitionFeePaymentRepo;
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



    @GetMapping("/tuitionFee")
    public String colectTuitnFee(Model model) {
        List<String> idlist = studentRepo.getStudentIds();
        model.addAttribute("studentIDS",idlist);
        return "/pages/financial/tuitionFee";
    }

    @GetMapping("/tuitionFee/due")
    public String tuitnFeeDue() {
        return "/pages/financial/StWithTuitionDue";
    }

    @GetMapping("/tuitionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return tuitionFeePaymentService.getTuitionFeePaymentId();
    }

    @PostMapping(value = "/tuitionFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String tuitionFeePaymentJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        TuitionFeePayment tuitionFeePayment = gson.fromJson(tuitionFeePaymentJson, TuitionFeePayment.class);
        System.out.println(tuitionFeePayment.getTuitionFeeDue());
        tuitionFeePayment.setYear(LocalDate.now().getYear()+"");
        tuitionFeePaymentRepo.save(tuitionFeePayment);
        return "redirect:/tuitionFee";
    }


    @GetMapping("/tuitionFee/tfDue")
    @ResponseBody
    public List<TuitionFeeDueReport> getStudentsWtihTuionDue(Model model) {
        List<ITuitionFeeDueReport>  p = tuitionFeePaymentRepo.getTuitionFeeDueReport();
        List<TuitionFeeDueReport> studentListWithDue = new ArrayList<>();
        for (ITuitionFeeDueReport s:p) {
            TuitionFeeDueReport tfp = new TuitionFeeDueReport();
            tfp.setName(s.getName());
            tfp.setStID(s.getStudentId());
            tfp.setDob(s.getDob());
            tfp.setDue(s.getDue());
            studentListWithDue.add(tfp);
        }
        model.addAttribute("studentListWithDues","studentListWithDue");

        System.out.println(studentListWithDue.size());
        return studentListWithDue;
    }

    @GetMapping("/tuitionFee/{pid}")
    @ResponseBody
    public double getAmount(@PathVariable String pid) {

        double totalAmount;

        Object amount = cashRepo.getAmount(pid);
        double cash = (amount != null? Double.parseDouble(amount.toString()):0);
        System.out.println(amount);

        amount = chequeRepo.getAmount(pid);
        double cheque = (amount != null? Double.parseDouble(amount.toString()):0);
        System.out.println(amount);

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

    @GetMapping(value = "/tf/{stId}/{month}/{year}")
    @ResponseBody
    public TuitionFeePayment getTFPayemnt(@PathVariable String stId,@PathVariable String month,@PathVariable String year){
        return tuitionFeePaymentRepo.findTuitionFeePaymentByStudentIdAndMonthAndYear(stId,month,year);
    }

    @GetMapping(value = "/tf/all")
    public String getAllAFPayments(Model model){
        model.addAttribute("tfPaymentList",tuitionFeePaymentRepo.findAll());
        return "/pages/tables/tfPayments";
    }

    @GetMapping(value = "/tf/details/{tfPaymentId}")
    public String afDetails(Model model,@PathVariable String tfPaymentId){
        model.addAttribute("cashes",cashRepo.findAllByPaymentId(tfPaymentId));
        model.addAttribute("cheques",chequeRepo.findAllByPaymentId(tfPaymentId));
        model.addAttribute("ccs",creditCardRepo.findAllByPaymentId(tfPaymentId));
        model.addAttribute("mOrders",moneyOrderRepo.findAllByPaymentId(tfPaymentId));
        model.addAttribute("fromSals",fromSalRepo.findAllByPaymentId(tfPaymentId));
        model.addAttribute("zelles",zelleReo.findAllByPaymentId(tfPaymentId));
        // System.out.println(chequeRepo.findChequeByStudentId(stId,year).get(0).getChequeImg());
        return "/pages/tables/tfPaymentDetails";
    }

    @GetMapping(value = "/tf/delete/{tfPaymentId}")
    public String deleteAFPayment(@PathVariable String tfPaymentId){
        tuitionFeePaymentRepo.deleteTuitionFeePaymentByTfPaymentId(tfPaymentId);
        cashRepo.deleteCashByPaymentId(tfPaymentId);
        chequeRepo.deleteChequeByPaymentId(tfPaymentId);
        creditCardRepo.deleteCreditCardByPaymentId(tfPaymentId);
        fromSalRepo.deleteFromSalsByPaymentId(tfPaymentId);
        moneyOrderRepo.deleteMoneyOrderByPaymentId(tfPaymentId);
        zelleReo.deleteZelleByPaymentId(tfPaymentId);
        return "redirect:/tf/all";
    }

}
