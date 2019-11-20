package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.entity.*;
import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PublisherService publisherService;
    @Autowired
    CourseService courseService;

    @Autowired
    SliderImageService sliderImageService;




    @GetMapping("addBook")
    public String bookUploadForm(Model model) {
        model.addAttribute("book",new Book());
        model.addAttribute("authorNames",authorService.authorNames());
        model.addAttribute("publishers",publisherService.publisherNames());
        model.addAttribute("deptNames",departmentService.deptNames());
        model.addAttribute("courseNames",courseService.courseNames());
        return "dashboardPages/bookUploadForm";
    }

    @PostMapping("saveBook")
    public String saveBook(Book book, @RequestParam("frontCover") MultipartFile[] frontCover) {

        List<Author> authorList = authorService.getAuthorList(book.getAuthorNames());
        Publisher publisher = publisherService.getPublisher(book.getPublisherName());
        List<Course> courseList = courseService.getCourseList(book.getCourseNames());
        List<Department> deptList = departmentService.getDeptList(book.getDeptNames());

        for (MultipartFile image: frontCover) {
            try {
                System.out.println(sliderImageService.getBase64Image(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        List<BookImage> imagess = new ArrayList<>();
        BookImage bookImage = new BookImage();
        bookImage.setImgUrl("ffffff");
        imagess.add(bookImage);

        book.setAuthors(authorList);
        book.setPublisher(publisher);
        book.setCourses(courseList);
        book.setDepts(deptList);

        return "redirect:/addBook";
    }
}
