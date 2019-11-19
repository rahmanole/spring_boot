package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {
    List<Book> getBooksByDept(String deptName);
    List<String> deptNames();
    List<Department> getDeptList(List<String> deptNames);
}
