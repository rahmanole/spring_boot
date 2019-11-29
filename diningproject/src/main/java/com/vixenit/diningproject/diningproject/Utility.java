package com.vixenit.diningproject.diningproject;

import com.vixenit.diningproject.diningproject.entity.MemberInfo;
import com.vixenit.diningproject.diningproject.repos.DeptRepo;
import com.vixenit.diningproject.diningproject.repos.MemberInfoRepo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

public class Utility {
    @Autowired
    static DeptRepo deptRepo;
    @Autowired
    static MemberInfoRepo memberInfoRepo;

    public static String generateCardNo(MemberInfo memberInfo) {
        String session = memberInfo.getSession();
        String cardNo = "";
        cardNo += session.substring(session.lastIndexOf("-"))
                + deptRepo.getDeptID(memberInfo.getDeptName())
                + memberInfoRepo.getDeptSesnWisrMemNum(memberInfo.getSession(),memberInfo.getDeptName());
        return cardNo;
    }
}
