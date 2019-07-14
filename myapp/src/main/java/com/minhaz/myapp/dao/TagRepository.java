package com.minhaz.myapp.dao;

import com.minhaz.myapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByTagName(String tagName);
}
