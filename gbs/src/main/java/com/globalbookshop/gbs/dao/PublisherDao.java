package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherDao extends JpaRepository<Publisher,Long> {

    String QUERY_FOR_PUBLISHER_NAMES = "Select publisher from publishers";
    Publisher findPublisherByPublisher(String publisher);

    @Query(nativeQuery = true,value = QUERY_FOR_PUBLISHER_NAMES)
    List<String> publisherNames();

}
