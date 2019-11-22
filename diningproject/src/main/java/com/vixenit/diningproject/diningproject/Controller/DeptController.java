package com.vixenit.diningproject.diningproject.Controller;

import com.vixenit.diningproject.diningproject.entity.Dept;
import com.vixenit.diningproject.diningproject.repos.DeptDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeptController {
    @Autowired
    DeptDao deptDao;

    @RequestMapping("/addDept")
    public String addDept(Model model){
        model.addAttribute("dept",new Dept());
        return "pages/forms/addDept";
    }

    @PostMapping("/saveDept")
    public String saveDept(Dept dept){
        int i = deptDao.getMaxDeptId();
        dept.setDeptId(++i);
        deptDao.save(dept);
        return "redirect:/addDept";
    }
}
