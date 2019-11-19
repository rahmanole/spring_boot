package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.*;
import com.globalbookshop.gbs.entity.*;
import com.globalbookshop.gbs.service.FileUploadService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImp implements FileUploadService {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    PublisherDao publisherDao;

    @Override
    public boolean uploadFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        final String UPLOADED_FOLDER = "src/main/resources/files/";
        boolean fileUploadFlag = false;
        String filePath = "";
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

            fileUploadFlag = true;


            filePath = UPLOADED_FOLDER + file.getOriginalFilename();

            //===========Catching the sheet==========
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            //===========end=====

            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbook.getSheetAt(0);
            long startTime = System.currentTimeMillis();
            outer:
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Book book = new Book();
                Row row = sheet.getRow(i);

                if (row == null) {
                    System.out.println("row null");
                    break;
                } else {
                    for (int cn = row.getFirstCellNum(); cn < 20; cn++) {
                        Cell cell = row.getCell(cn);
                        if (cell == null) {
                            break outer;
                        } else {
                            String cellValue = dataFormatter.formatCellValue(cell).trim();

                            if (cellValue.isEmpty())
                                break outer;
                            if (cn == 1 && bookDao.findBookByIsbn(cellValue) != null)
                                continue outer;

                            setProperties(cn, cellValue, book);

                        }
                    }
                }

                bookDao.save(book);

            }
            System.out.println(System.currentTimeMillis() - startTime);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileUploadFlag) {
                Path fileToDelete = Paths.get(filePath);
                try {

                    Files.delete(fileToDelete);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return false;
    }

    private void setProperties(int colIndex, String cellValue, Book book) {
        switch (colIndex) {
            case 0:
                book.setTitle(cellValue);
                break;
            case 1:
                book.setIsbn(cellValue);
                break;
            case 2:
                book.setAuthors(getAuthorList(cellValue));
                break;
            case 3:
                book.setCopyrightYear(cellValue);
                break;
            case 4:
                book.setAvailablity(Integer.parseInt(cellValue));
                break;
            case 5:
                book.setListPrice(Double.parseDouble(cellValue));
                break;
            case 6:
                book.setDiscount(Double.parseDouble(cellValue));
                break;
            case 7:
                book.setPublisher(getPublisher(cellValue));
                break;
            case 8:
                book.setImprint(cellValue);
                break;
            case 9:
                book.setFormate(cellValue);
                break;
            case 10:
                book.setEditionType(cellValue);
                break;
            case 11:
                book.setPages(Integer.parseInt(cellValue));
                break;
            case 12:
                book.setPrintOrigin(cellValue);
                break;
            case 13:
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(cellValue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                book.setPublicationDate(date);
                break;
            case 14:
                book.setDimentions(cellValue);
                break;
            case 15:
                book.setWeight(Double.parseDouble(cellValue));
                break;
            case 16:
                book.setDepts(getDeptList(cellValue));
                break;
            case 17:
                book.setCourses(getCourseList(cellValue));
                break;
            case 18:
                book.setRestriction(cellValue);
                break;
            case 19:
                book.setOverview(cellValue);
                break;
        }
    }

    private List<Author> getAuthorList(String cellValue) {
        String[] authorNames = cellValue.split(",");
        List<Author> authorList = new ArrayList<>();

        for (String authorName : authorNames) {
            Author author;
            author = authorDao.findAuthorByAuthorName(authorName);
            if (author == null) {
                author = new Author();
                author.setAuthorName(authorName);
                authorDao.save(author);
                author = authorDao.findAuthorByAuthorName(authorName);
            }
            authorList.add(author);
        }
        return authorList;
    }

    private List<Course> getCourseList(String cellValue) {
        String[] courseNames = cellValue.split(",");
        List<Course> courseList = new ArrayList<>();
        for (String courseName : courseNames) {
            Course course;
            course = courseDao.findCourseByCourseName(courseName);
            if (course == null) {
                course = new Course();
                course.setCourseName(courseName);
                courseDao.save(course);
                course = courseDao.findCourseByCourseName(courseName);

            }
            courseList.add(course);
        }
        return courseList;
    }

    private List<Department> getDeptList(String cellValue) {
        String[] courseNames = cellValue.split(",");
        List<Department> deptList = new ArrayList<>();
        for (String departmentName : courseNames) {
            Department department;
            department = departmentDao.findDepartmentByDepartmentName(departmentName);
            if (department == null) {
                department = new Department();
                department.setDepartmentName(departmentName);
                departmentDao.save(department);
                department = departmentDao.findDepartmentByDepartmentName(departmentName);

            }
            deptList.add(department);
        }
        return deptList;
    }

    private Publisher getPublisher(String cellValue) {
        Publisher publisher;

        publisher = publisherDao.findPublisherByPublisher(cellValue);
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setPublisher(cellValue);
            publisherDao.save(publisher);
            publisher = publisherDao.findPublisherByPublisher(cellValue);
        }

        return publisher;
    }
}
