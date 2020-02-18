package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.Cheque;
import com.duny.fcr.entity.TuitionFeePayment;
import com.duny.fcr.repo.*;
import com.duny.fcr.service.AdmissionPaymentService;
import com.duny.fcr.serviceImp.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
public class AdmissionPaymentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    AdmissionPaymentService admissionPaymentService;
    @Autowired
    AdmissionPaymentRepo admissionPaymentRepo;
    @Autowired
    CashRepo cashRepo;
    @Autowired
    ChequeRepo chequeRepo;
    @Autowired
    CreditCardRepo creditCardRepo;
    @Autowired
    FromSalRepo fromSalRepo;
    @Autowired
    MoneyOrderRepo moneyOrderRepo;
    @Autowired
    ZelleReo zelleReo;



    @GetMapping("/admissionFee")
    public String colectAdmisnFee(Model model) {
        List<String> idlist = studentRepo.getStudentIds();
        model.addAttribute("studentIDS",idlist);
        return "/pages/financial/admissionFee";
    }

//    @GetMapping("/admissionFee/getPaymentId")
//    @ResponseBody
//    public String getPaymentId() {
//        return admissionPaymentService.getAdmissionFeePaymentId();
//    }

    @PostMapping(value = "/admFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String admPaymentJson){
        System.out.println(admPaymentJson);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        AdmissionPayment admissionPayment;
        admissionPayment = gson.fromJson(admPaymentJson, AdmissionPayment.class);
        admissionPayment.setYear(LocalDate.now().getYear()+"");
        admissionPaymentRepo.save(admissionPayment);
        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/af/acFee/{id}")
    public String insertAcademicFee(@PathVariable long id){
        admissionPaymentRepo.updatingAcademicee(id);
        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/af/mealFee/{id}")
    public String insertMealFee(@PathVariable long id){
        admissionPaymentRepo.updatingMealFee(id);
        return "redirect:/admissionFee";
    }

    @GetMapping(value = "/af/{pid}")
    @ResponseBody
    public AdmissionPayment getAFPayemntID(@PathVariable String pid){
        return admissionPaymentRepo.findAdmissionPaymentByAfPaymentId(pid);
    }

    @GetMapping(value = "/af/all")
    public String getAllAFPayments(Model model){
        List<AdmissionPayment> afPaymentList = admissionPaymentRepo.findAll();
        model.addAttribute("afPaymentList",afPaymentList);
        return "/pages/tables/afPayments";
    }

    @GetMapping(value = "/af/delete/{paymentId}")
    public String deleteAFPayment(@PathVariable String paymentId){
        admissionPaymentRepo.deleteAFPaymentByAfPaymentId(paymentId);
        cashRepo.deleteCashByPaymentId(paymentId);
        chequeRepo.deleteChequeByPaymentId(paymentId);
        creditCardRepo.deleteCreditCardByPaymentId(paymentId);
        fromSalRepo.deleteFromSalsByPaymentId(paymentId);
        moneyOrderRepo.deleteMoneyOrderByPaymentId(paymentId);
        zelleReo.deleteZelleByPaymentId(paymentId);
        return "redirect:/af/all";
    }

    @GetMapping(value = "/af/details/{afPaymentId}")
    public String afDetails(Model model,@PathVariable String afPaymentId){
        model.addAttribute("cashes",cashRepo.findAllByPaymentId(afPaymentId));
        model.addAttribute("cheques",chequeRepo.findAllByPaymentId(afPaymentId));
        model.addAttribute("ccs",creditCardRepo.findAllByPaymentId(afPaymentId));
        model.addAttribute("mOrders",moneyOrderRepo.findAllByPaymentId(afPaymentId));
        model.addAttribute("fromSals",fromSalRepo.findAllByPaymentId(afPaymentId));
        model.addAttribute("zelles",zelleReo.findAllByPaymentId(afPaymentId));
        model.addAttribute("pid",afPaymentId);
        // System.out.println(chequeRepo.findChequeByStudentId(stId,year).get(0).getChequeImg());
        return "/pages/tables/tfPaymentDetails";
    }

}
