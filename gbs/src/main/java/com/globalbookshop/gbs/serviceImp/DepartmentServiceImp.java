package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.DepartmentDao;
import com.globalbookshop.gbs.entity.Book;
import com.globalbookshop.gbs.entity.Department;
import com.globalbookshop.gbs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<Book> getBooksByDept(String deptName) {
        return departmentDao.findDepartmentByDepartmentName(deptName).getBooks();
    }

    @Override
    public List<String> deptNames() {
        List<String> deptNames = departmentDao.deptNames();
        Collections.sort(deptNames);
        return deptNames;
    }


    @Override
    public List<Department> getDeptList(List<String> deptNames) {
        List<Department> deptList = new ArrayList<>();
        deptNames.forEach(coursName ->{
            deptList.add(departmentDao.findDepartmentByDepartmentName(coursName));
        });
        return deptList;
    }
}
