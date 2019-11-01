package com.globalbookshop.gbs.serviceImp;

import com.globalbookshop.gbs.service.FileUpload;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadImp implements FileUpload {

    @Override
    public boolean uploadFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        final String UPLOADED_FOLDER = "src/main/resources/files/";
        boolean fileUploadFlag = false;
        String filePath = "";
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            System.out.println(file.getOriginalFilename());
            Files.write(path, bytes);

            fileUploadFlag =true;

            System.out.println("=============== saved success ============");

            filePath = UPLOADED_FOLDER + file.getOriginalFilename();

            //===========Catching the sheet==========
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            System.out.println("Uploaded workbook has "+workbook.getNumberOfSheets()+" sheets");
            //===========end=====


            workbook.forEach(sheet->{
                System.out.println(sheet.getSheetName());
            });
            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbook.getSheetAt(0);
            sheet.forEach(row->{
                row.forEach(cell->{
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.println(cell.getRowIndex());

                    System.out.println(cellValue);

                });
                System.out.println();
            });

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileUploadFlag){
                Path fileToDelete = Paths.get(filePath);
                try {
                    Files.delete(fileToDelete);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return  false;
    }
}
