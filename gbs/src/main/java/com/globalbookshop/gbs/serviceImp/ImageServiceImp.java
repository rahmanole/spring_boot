package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ImageServiceImp implements ImageService {

    public boolean getBase64Image(MultipartFile file) throws Exception {
        final String UPLOADED_FOLDER = "src/main/resources/files/";
        boolean fileUploadFlag = false;
        String filePath = "";
        try {

            byte[] bytes = file.getBytes();

            fileUploadFlag=false;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileUploadFlag;
    }

}
