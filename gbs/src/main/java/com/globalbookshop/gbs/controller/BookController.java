package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.dao.BookDao;
import com.globalbookshop.gbs.dao.BookImageDao;
import com.globalbookshop.gbs.entity.*;
import com.globalbookshop.gbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    FileUploadService fileUploadService;


    @Autowired
    BookImageDao bookImageDao;

    @Autowired
    SliderImageService sliderImageService;

    @Autowired
    BookDao bookDao;


    @GetMapping("addBook")
    public String bookUploadForm(Model model) {
        Book book = new Book();


        model.addAttribute("book", book);
        model.addAttribute("authorNames", authorService.authorNames());
        model.addAttribute("publishers", publisherService.publisherNames());
        model.addAttribute("deptNames", departmentService.deptNames());
        model.addAttribute("courseNames", courseService.courseNames());
        return "dashboardPages/bookUploadForm";
    }

    @PostMapping("saveBook")
    public String saveBook(Book book, @RequestParam("frontCover") MultipartFile frontCover,
                           @RequestParam("backCover") MultipartFile backCover,
                           @RequestParam("pubDate") String pubDate) throws Exception {

        String imgFront = "data:image/jpg;base64," + sliderImageService.getBase64Image(frontCover);
        String imgBack = "data:image/jpg;base64," + sliderImageService.getBase64Image(backCover);

        BookImage backImg = new BookImage();
        BookImage frontImg = new BookImage();
        frontImg.setImgUrl(imgFront);
        backImg.setImgUrl(imgBack);

        List<BookImage> bookImages = new ArrayList<>();
        bookImages.add(frontImg);
        bookImages.add(backImg);

        book.setBookImages(bookImages);


        book.setAuthors(fileUploadService.getAuthorList(book.getAuthorNames()));
        book.setPublisher(fileUploadService.getPublisher(book.getPublisherName()));
        book.setCourses(fileUploadService.getCourseList(book.getCourseNames()));
        book.setDepts(fileUploadService.getDeptList(book.getDeptNames()));

        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(pubDate);
        book.setPublicationDate(date);
        bookService.saveBook(book);

        return "redirect:/addBook";
    }

    @RequestMapping("/bookUpdate/{id}")
    public String bookUpdate(Model model,@PathVariable Long id){

        Book book = bookService.getBook(id);
        String[] authors = new String[book.getAuthors().size()];
        for (int i=0;i<book.getAuthors().size();i++){
            authors[i] = book.getAuthors().get(i).getAuthorName();
        }

        String[] courses = new String[book.getCourses().size()];
        for (int i =0;i<book.getCourses().size();i++){
            courses[i] = book.getCourses().get(i).getCourseName();
        }
        String[] depts = new String[book.getDepts().size()];
        for (int i =0;i<book.getDepts().size();i++){
            depts[i] = book.getDepts().get(i).getDepartmentName();
        }




        book.setAuthorNames(authors);
        book.setCourseNames(courses);
        book.setDeptNames(depts);
        model.addAttribute("book",book);
        model.addAttribute("allAuthors", authorService.authorNames());
        model.addAttribute("publishers", publisherService.publisherNames());
        model.addAttribute("deptNames", departmentService.deptNames());
        model.addAttribute("courseNames", courseService.courseNames());
        return "dashboardPages/bookUpdateForm";
    }

    @RequestMapping("/details/{id}")
    public String bookDetails(Model model,@PathVariable("id") Long id){

        model.addAttribute("book",bookDao.getOne(id));
        return "bookDetails";
    }

    @RequestMapping("/bookList")
    public String bookList(){

        return "dashboardPages/bookList";
    }
}
