package com.vixenit.diningproject.diningproject.repos;

import com.vixenit.diningproject.diningproject.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepo extends JpaRepository<MemberInfo,Long> {
    String GET_DEPT_SESSON_WISE_TOTAL_MEMBER_NUM = "select count(*) from member_info where session = ?1 and dept_name = ?2";

    @Query(value = GET_DEPT_SESSON_WISE_TOTAL_MEMBER_NUM,nativeQuery = true)
    int getDeptSesnWisrMemNum(String session,String dept);
}
