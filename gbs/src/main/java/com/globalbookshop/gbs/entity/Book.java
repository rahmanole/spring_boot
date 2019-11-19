package com.globalbookshop.gbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100,nullable = false)
    private String title;

    @Transient
    private ArrayList<String> authorNames = new ArrayList();
    @Transient
    private ArrayList<String> deptNames = new ArrayList();
    @Transient
    private ArrayList<String> courseNames = new ArrayList();
    @Transient
    private String publisherName;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;
    @Column(unique = true,nullable = false)
    private String isbn;
    @Column(name = "copyright_year")
    private String copyrightYear;
    private int availablity;
    private double listPrice;
    private double discount;

    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("books")
    private Publisher publisher;
    @Column(length = 30)
    private String imprint;
    @Column(length = 30)
    private String formate;
    @Column(name="edition_type",length = 30)
    private String editionType;
    private int pages;
    @Column(length = 30)
    private String printOrigin;

    private Date publicationDate;
    @Column(length = 30)
    private String dimentions;
    private  double weight;

    @Column(name = "cover_img", columnDefinition = "MEDIUMTEXT",length = 500000,nullable = false)
    private String coverImg;
    @Column(name = "back_img", columnDefinition = "MEDIUMTEXT",length = 500000)
    private String backImg;
    @Column(name = "other_img", columnDefinition = "MEDIUMTEXT",length = 500000)
    private String otherImg;
//
//    @Transient
//    private List<File> mImages;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_dept",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id")}
    )
    @JsonIgnoreProperties("books")
    private List<Department> depts;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_course",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @JsonIgnoreProperties("books")
    private List<Course> courses;
    @Column(length = 30)
    private String restriction;
    @Column(columnDefinition = "TEXT")
    private String overview;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public int getAvailablity() {
        return availablity;
    }

    public void setAvailablity(int availablity) {
        this.availablity = availablity;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String getFormate() {
        return formate;
    }

    public void setFormate(String formate) {
        this.formate = formate;
    }

    public String getEditionType() {
        return editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPrintOrigin() {
        return printOrigin;
    }

    public void setPrintOrigin(String printOrigin) {
        this.printOrigin = printOrigin;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDimentions() {
        return dimentions;
    }

    public void setDimentions(String dimentions) {
        this.dimentions = dimentions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(String otherImg) {
        this.otherImg = otherImg;
    }

    public List<Department> getDepts() {
        return depts;
    }

    public void setDepts(List<Department> depts) {
        this.depts = depts;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public ArrayList<String> getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(ArrayList<String> deptNames) {
        this.deptNames = deptNames;
    }

    public ArrayList<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(ArrayList<String> courseNames) {
        this.courseNames = courseNames;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
