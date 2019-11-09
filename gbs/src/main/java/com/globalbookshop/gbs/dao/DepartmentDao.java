package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department,Long> {
    Department findDepartmentByDepartmentName(String departmentName);
}
