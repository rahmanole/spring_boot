package com.minhaz.myapp.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false,length = 100)
    private String publisherGivenId;
    private String cat;

    @ManyToMany( cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tag",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false)
    private Date dateTime;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String heading;
    private String ftrImg;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable = false)
    private List<Para> postBody;
    @Column(nullable = false)
    private String publisher;


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
