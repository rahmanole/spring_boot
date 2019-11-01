package com.globalbookshop.gbs.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30,unique = true,nullable = false)
    private String title;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;
    @Column(name = "copyright_year")
    private int copyrightYear;
    private int availablity;
    private double listPrice;
    private double discount;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_pub",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "pub_id")}
    )
    private List<Publisher> publishers;
    @Column(length = 30,unique = true)
    private String imprint;
    @Column(length = 30,unique = true)
    private String formate;
    @Column(name="edition_type",length = 30,unique = true)
    private String editionType;
    private int pages;
    @Column(length = 30,unique = true)
    private String printOrigin;
    private Date publicationDate;
    @Column(length = 30,unique = true)
    private String dimentions;
    private  double weight;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_img",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "img_id")}
    )
    private List<Image> images;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_dept",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id")}
    )
    private List<Department> depts;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_course",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
    @Column(length = 30,unique = true)
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(int copyrightYear) {
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

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
}
