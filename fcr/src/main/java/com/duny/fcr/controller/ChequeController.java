package com.duny.fcr.controller;

import com.duny.fcr.entity.Cheque;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class ChequeController {

    @PostMapping("/cheque/save")
    public String saveCheque(@RequestParam("paymentId")String pid,
                             @RequestParam("accountNum") String acNum,
                             @RequestParam("chequeNum") String cNum,
                             @RequestParam("chequeDate") String cDate,
                             @RequestParam("amount") String amnt,
                             @RequestParam("chequeImg") MultipartFile file) throws IOException {
        System.out.println(pid+acNum+cNum+cDate+amnt);
        System.out.println(Base64.getEncoder().encodeToString(file.getBytes()));
        return "redirect:/admissionFee";
    }
}
