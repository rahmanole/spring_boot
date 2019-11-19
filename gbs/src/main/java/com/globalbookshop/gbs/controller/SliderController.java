package com.globalbookshop.gbs.controller;

import com.globalbookshop.gbs.dao.SliderImgDao;
import com.globalbookshop.gbs.entity.SliderImage;
import com.globalbookshop.gbs.service.SliderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SliderController {

    @Autowired
    SliderImageService sliderImageService;
    @Autowired
    SliderImgDao sliderImgDao;

    @GetMapping("sliders")
    public String sliders(Model model,SliderImage slider) {
        model.addAttribute("sliderList",sliderImageService.getAllSliders());
        model.addAttribute("slider",slider);
        return "dashboardPages/sliders";
    }

    @GetMapping("uploadSliders")
    public String uploadSliders(Model model) {
        model.addAttribute("slider",new SliderImage());
        return "dashboardPages/uploadSliders";
    }

    @GetMapping("deleteSlider/{id}")
    public String deleteSlider(@PathVariable long id) {
        sliderImageService.deleteSlider(id);
        return "redirect:/sliders";
    }

    @GetMapping("updateSlider/{id}")
    public String updateSlider(Model model,@PathVariable long id) {
        model.addAttribute("slider",sliderImgDao.getOne(id));
        return "dashboardPages/updateSlider";
    }

    @PostMapping("sliderUploader")
    public String uploadImage(@RequestParam("file") MultipartFile file, SliderImage slider, RedirectAttributes redirectAttributes) {

        try {
            if(file.isEmpty()){
                redirectAttributes.addFlashAttribute("message","Please Select an image");
                return "redirect:/sliders";
            }else {

                slider.setImgUrl("data:image/jpeg;base64,"+sliderImageService.getBase64Image(file));
                sliderImageService.saveSlider(slider);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/sliders";
    }

}
