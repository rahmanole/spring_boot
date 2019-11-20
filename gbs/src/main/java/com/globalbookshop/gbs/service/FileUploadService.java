package com.globalbookshop.gbs.service;

import com.globalbookshop.gbs.entity.Author;
import com.globalbookshop.gbs.entity.Course;
import com.globalbookshop.gbs.entity.Department;
import com.globalbookshop.gbs.entity.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public interface FileUploadService {
    boolean uploadFile(MultipartFile file, RedirectAttributes redirectAttributes);
    List<Author> getAuthorList(String[] authorNames);
    List<Course> getCourseList(String[] courseNames);
    List<Department> getDeptList(String[] courseNames);
    Publisher getPublisher(String cellValue);

}
