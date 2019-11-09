package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherDao extends JpaRepository<Publisher,Long> {
    Publisher findPublisherByPublisher(String publisher);
}
