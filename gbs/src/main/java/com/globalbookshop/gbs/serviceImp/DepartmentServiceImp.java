package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.DepartmentDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<Book> getBooksByDept(String deptName) {
        return departmentDao.findDepartmentByDepartmentName(deptName).getBooks();
    }
}
