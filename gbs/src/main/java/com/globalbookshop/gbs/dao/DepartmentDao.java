package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentDao extends JpaRepository<Department,Long> {

    String QUERY_FOR_DEPT_NAMES = "Select department_name from departments";

    Department findDepartmentByDepartmentName(String departmentName);

    @Query(nativeQuery = true,value = QUERY_FOR_DEPT_NAMES)
    List<String> deptNames();

}
