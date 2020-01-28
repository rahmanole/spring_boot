package com.duny.fcr.controller;

import com.duny.fcr.entity.AdmissionPayment;
import com.duny.fcr.entity.Cheque;
import com.duny.fcr.repo.*;
import com.duny.fcr.service.AdmissionPaymentService;
import com.duny.fcr.serviceImp.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admissionFee/getPaymentId")
    @ResponseBody
    public String getPaymentId() {
        return admissionPaymentService.getAdmissionFeePaymentId();
    }

    @PostMapping(value = "/admFee/save", consumes = "application/json", produces = "application/json")
    public String saveCash(@RequestBody String admPaymentJson){
        System.out.println(admPaymentJson);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        AdmissionPayment admissionPayment;
        admissionPayment = gson.fromJson(admPaymentJson, AdmissionPayment.class);
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

    @GetMapping(value = "/af/{stId}/{year}")
    @ResponseBody
    public AdmissionPayment getAFPayemntID(@PathVariable String stId,@PathVariable String year){
        return admissionPaymentRepo.getAdmissionPaymentByStudentIdAndYear(stId,year);
    }

    @GetMapping(value = "/af/all")
    public String getAllAFPayments(Model model){
        model.addAttribute("afPaymentList",admissionPaymentRepo.findAll());
        return "/pages/tables/afPayments";
    }

    @GetMapping(value = "/af/delete/{stId}")
    public String deleteAFPayment(@PathVariable String stId){
        admissionPaymentRepo.deleteAFPaymentByStudentId(stId);
        cashRepo.deleteCashByStudentId(stId);
        chequeRepo.deleteChequeByStudentId(stId);
        chequeRepo.deleteChequeByStudentId(stId);
        creditCardRepo.deleteCreditCardByStudentId(stId);
        fromSalRepo.deleteFromSalsByStudentId(stId);
        moneyOrderRepo.deleteMoneyOrderByStudentId(stId);
        zelleReo.deleteZelleByStudentId(stId);
        return "redirect:/af/all";
    }

    @GetMapping(value = "/af/details/{stId}")
    public String afDetails(Model model,@PathVariable String stId){
       model.addAttribute("cashes",cashRepo.findCashByStudentId(stId));
        model.addAttribute("cheques",chequeRepo.findChequeByStudentId(stId));
        System.out.println(chequeRepo.findChequeByStudentId(stId).get(0).getChequeImg());
        return "/pages/tables/paymentDetails";
    }
}
