package com.vixenit.diningproject.diningproject.repos;

import com.vixenit.diningproject.diningproject.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptDao extends JpaRepository<Dept,Long> {
    String GET_MAX_DEPT_ID = "select max(dept_id) from depts";

    @Query(nativeQuery = true,value = GET_MAX_DEPT_ID)
    int getMaxDeptId();
}
