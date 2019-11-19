package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.dao.SliderImgDao;
import com.globalbookshop.gbs.entity.SliderImage;
import com.globalbookshop.gbs.service.SliderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class SliderImageServiceImp implements SliderImageService {
    @Autowired
    SliderImgDao sliderImgDao;

    @Override
    public String getBase64Image(MultipartFile file) {
        String imgString = "";
        try {

            byte[] bytes = file.getBytes();

            imgString = Base64.getEncoder().encodeToString(bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgString;
    }

    @Override
    public boolean saveSlider(SliderImage sliderImage) {
        try {
            System.out.println("=================="+sliderImage.getImgUrl().length());
            sliderImgDao.save(sliderImage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SliderImage> getAllSliders(){
        List<SliderImage>  sliders = sliderImgDao.findAll();
        Collections.reverse(sliders);
        return sliders;
    }

    @Override
    public void deleteSlider(long id) {
        sliderImgDao.deleteById(id);
    }

}
