package com.duny.fcr.controller;

import com.duny.fcr.dto.SpAssignDTO;
import com.duny.fcr.entity.FinDtlsOfStudent;
import com.duny.fcr.repo.FeeRepo;
import com.duny.fcr.repo.FinDtlsOfStudentRepo;
import com.duny.fcr.repo.SponsorRepo;
import com.duny.fcr.repo.StudentRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class FeeController {


    @Autowired
    SponsorRepo sponsorRepo;
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    FeeRepo feeRepo;

    @Autowired
    FinDtlsOfStudentRepo finDtlsOfStudentRepo;

    @GetMapping("/fee/report/{id}")
    public String generateFee(Model model, @PathVariable long id) {
        model.addAttribute("studentIDS", studentRepo.getStudentIds());
        return "pages/feeReport";
    }

    @PostMapping(value = "/fee/spAssing")
    public String spAssign(@RequestBody String spAssingDTO) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        SpAssignDTO dto = gson.fromJson(spAssingDTO, SpAssignDTO.class);
        feeRepo.inserSpID(dto.getSp_id(), dto.getFin_dtl_id());
        feeRepo.inserStID(dto.getSt_id(), dto.getSp_id());
        return "redirect:/pages/report/1";
    }

    @PostMapping(value = "/fee/daddAssign")
    public String daddAssign(@RequestBody String spAssingDTO) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();

        SpAssignDTO dto = gson.fromJson(spAssingDTO, SpAssignDTO.class);

        feeRepo.inserDaddIDInFin(dto.getFin_dtl_id());
        feeRepo.inserStIDInDadd(dto.getSt_id(), dto.getDadd_id());
        return "redirect:/pages/report/1";
    }

    @GetMapping("/findetails/{id}")
    @ResponseBody
    public FinDtlsOfStudent getFindetails(@PathVariable long id) {
        return finDtlsOfStudentRepo.getOne(id);
    }


    @GetMapping("/sponsor/remover/{fin_id}/{sp_id}")
    public String removeSponsorFromFinDtls(@PathVariable("fin_id") long id,
                                         @PathVariable("sp_id") int sp_id) {
        finDtlsOfStudentRepo.removeSponsor(id);
        sponsorRepo.removeStudentFromSponsor(sp_id);

        return "redirect:/pages/report/1";
    }


}
