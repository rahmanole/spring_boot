package com.vixenit.diningproject.diningproject.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vixenit.diningproject.diningproject.entity.Image;
import com.vixenit.diningproject.diningproject.entity.MemberInfo;
import com.vixenit.diningproject.diningproject.repos.MemberInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.validation.constraints.Max;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class MemberInfoController {


    @Autowired
    MemberInfoRepo memberInfoRepo;

    @GetMapping("/member/registration")
    public String getMemberRegForm(Model model){
        model.addAttribute("memberinfo",new MemberInfo());
        return "pages/forms/memberRegForm.html";
    }

    @PostMapping("/member/save")
    public String save(MemberInfo memberInfo,@RequestParam("avtr") MultipartFile avtr,@RequestParam("bCard") MultipartFile bCard) throws IOException {

        Image avatar = new Image();
        Image boardingCard = new Image();
        avatar.setImgName("avatar");
        avatar.setImgUrl(Base64.getEncoder().encodeToString(avtr.getBytes()));
        boardingCard.setImgUrl(Base64.getEncoder().encodeToString(bCard.getBytes()));
        List<Image> images = new ArrayList<>();
        images.add(avatar);
        images.add(boardingCard);
        memberInfo.setImages(images);
        memberInfo.setApplicationDate(new Date());
        memberInfoRepo.save(memberInfo);
        return "redirect:/member/registration";
    }

    @GetMapping("/member/applications")
    public String getAllMmberInfo(Model model){
        model.addAttribute("appliedMemebers",memberInfoRepo.getAllAppliedMemebers());
        return "/pages/tables/appliedMembers.html";
    }
}
