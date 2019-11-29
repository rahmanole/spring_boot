package com.vixenit.diningproject.diningproject.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vixenit.diningproject.diningproject.entity.MemberInfo;
import com.vixenit.diningproject.diningproject.repos.MemberInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

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
    public String save(@RequestBody String memberInfoJson){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        MemberInfo memberInfo = gson.fromJson(memberInfoJson,MemberInfo.class);
        System.out.println(memberInfo.getDeptName());
        memberInfo.setApplicationDate(new Date());
        //memberInfoRepo.save(memberInfo);
        return "pages/forms/memberRegForm.html";
    }
}
