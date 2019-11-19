package com.globalbookshop.gbs.service;


import com.globalbookshop.gbs.entity.SliderImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface SliderImageService {
    String getBase64Image(MultipartFile file) throws Exception;
    boolean saveSlider(SliderImage sliderImage);
    List<SliderImage> getAllSliders();
    void deleteSlider(long id);
}
