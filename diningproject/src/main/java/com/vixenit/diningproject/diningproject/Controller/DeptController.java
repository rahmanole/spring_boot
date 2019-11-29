package com.vixenit.diningproject.diningproject.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vixenit.diningproject.diningproject.entity.Dept;
import com.vixenit.diningproject.diningproject.repos.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DeptController {
    @Autowired
    DeptRepo deptRepo;

    @GetMapping("dept/add")
    public String addDept(Model model) {
        model.addAttribute("dept", new Dept());
        return "pages/forms/addDept";
    }

    @PostMapping(value = "/dept/save", consumes = "application/json", produces = "application/json")
    public String createPerson(@RequestBody String deptJson) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = new Gson();
        Dept dept = gson.fromJson(deptJson,Dept.class);

        int i = deptRepo.getMaxDeptId();
        dept.setDeptId(++i);
        deptRepo.save(dept);
        return "redirect:/dept/add";
    }

    @GetMapping("dept/list")
    @ResponseBody
    public List<String> getList() {
        return deptRepo.getDeptNames();
    }


}
