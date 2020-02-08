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

    @GetMapping(value = "/af/delete/{stId}/{year}")
    public String deleteAFPayment(@PathVariable String stId,@PathVariable String year){
        admissionPaymentRepo.deleteAFPaymentByStudentId(stId);
        cashRepo.deleteCashByStudentId(stId,year);
        chequeRepo.deleteChequeByStudentId(stId,year);
        chequeRepo.deleteChequeByStudentId(stId,year);
        creditCardRepo.deleteCreditCardByStudentId(stId,year);
        fromSalRepo.deleteFromSalsByStudentId(stId,year);
        moneyOrderRepo.deleteMoneyOrderByStudentId(stId,year);
        zelleReo.deleteZelleByStudentId(stId,year);
        return "redirect:/af/all";
    }

    @GetMapping(value = "/af/details/{stId}/{year}")
    public String afDetails(Model model,@PathVariable String stId,@PathVariable String year){
        model.addAttribute("cashes",cashRepo.findCashByStudentId(stId,year));
        model.addAttribute("cheques",chequeRepo.findChequeByStudentId(stId,year));
        System.out.println(chequeRepo.findChequeByStudentId(stId,year).get(0).getChequeImg());
        return "/pages/tables/paymentDetails";
    }

}
