package com.vixenit.diningproject.diningproject.repos;

import com.vixenit.diningproject.diningproject.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepo extends JpaRepository<Dept,Long> {
    String GET_MAX_DEPT_ID = "select max(dept_id) from depts";
    String GET_DEPT_NAMEs = "select dept_name from depts";
    String GET_DEPT_ID = "select dept_id from depts where dept_name = ?1";

    @Query(nativeQuery = true,value = GET_MAX_DEPT_ID)
    int getMaxDeptId();
    @Query(nativeQuery = true,value = GET_DEPT_NAMEs)
    List<String> getDeptNames();
    @Query(nativeQuery = true,value = GET_DEPT_ID)
    int getDeptID(String name);
}
