package com.globalbookshop.gbs.dao;

import com.globalbookshop.gbs.entity.SliderImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderImgDao extends JpaRepository<SliderImage,Long> {
}
