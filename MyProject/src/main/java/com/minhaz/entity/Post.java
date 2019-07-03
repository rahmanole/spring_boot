package com.minhaz.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String publisherGivenId;
    private String cat;

    @Column(nullable = false)
    private Date dateTime;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String heading;
    private String ftrImg;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "post_para_table",joinColumns = @JoinColumn(name="post_id"),inverseJoinColumns = @JoinColumn(name="para_id"))
    private List<Para> postBody;
    @Column(nullable = false)
    private String publisher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getFtrImg() {
        return ftrImg;
    }

    public void setFtrImg(String ftrImg) {
        this.ftrImg = ftrImg;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Para> getPostBody() {
        return postBody;
    }

    public void setPostBody(List<Para> postBody) {
        this.postBody = postBody;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherGivenId() {
        return publisherGivenId;
    }

    public void setPublisherGivenId(String publisherGivenId) {
        this.publisherGivenId = publisherGivenId;
    }
}
