package com.duny.fcr.serviceImp;

import com.duny.fcr.entity.FinDtlsOfStudent;
import com.duny.fcr.entity.Student;
import com.duny.fcr.repo.StudentRepo;
import com.duny.fcr.service.StudentService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StudentServiceImp implements StudentService {

    @Override
    public void uploadExcel(MultipartFile file, StudentRepo studentRepo) {
        final String UPLOADED_FOLDER = "src/main/resources/files/";
        String filePath = "";
        Workbook workbook = null;
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);

            filePath = UPLOADED_FOLDER + file.getOriginalFilename();

            //===========Catching the sheet==========
            File students = new File(filePath);
            workbook = WorkbookFactory.create(students);
            //===========end=====

            DataFormatter dataFormatter = new DataFormatter();

            Sheet sheet = workbook.getSheetAt(0);
            long startTime = System.currentTimeMillis();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Student student = new Student();

                Row row = sheet.getRow(i);
                if (cellToString(row.getCell(0)).equals("")) {
                    System.out.println("breaking");
                    break;
                }


                student.setName(cellToString(row.getCell(0)));
                student.setGender(cellToString(row.getCell(1)));
                student.setDob(cellToDate(row.getCell(2)));
                student.setCurrentSchool(cellToString(row.getCell(3)));
                student.setCurrentGrade(cellToString(row.getCell(4)));
                student.setCourseName(cellToString(row.getCell(5)));
                student.setBoarding(cellToString(row.getCell(6)));
                student.setParentEmail(cellToString(row.getCell(7)));
                student.setEmail(cellToString(row.getCell(8)));
                student.setHomePhone(cellToString(row.getCell(9)));
                student.setMotherName(cellToString(row.getCell(10)));
                student.setMotherCell(cellToString(row.getCell(11)));
                student.setFatherName(cellToString(row.getCell(12)));
                student.setFatherCell(cellToString(row.getCell(13)));
                student.setDateOfAdmission(cellToDate(row.getCell(14)));
                student.setAddress(cellToString(row.getCell(15)));
                student.setAptNo(cellToString(row.getCell(16)));
                student.setCity(cellToString(row.getCell(17)));
                student.setState(cellToString(row.getCell(18)));
                student.setZip(cellToString(row.getCell(19)));
                student.setFinDtlsOfStudent(new FinDtlsOfStudent());

                student.setStatus("applied");
                int appId = 0;
                String stId = null;

                try {
                    appId = studentRepo.getMaxApplicationId();
                    stId = studentRepo.getMaxStudentId();
                }catch (Exception ex){

                }
                if(appId>0){
                    int application_id = studentRepo.getMaxApplicationId() + 1;
                    student.setApplicationId(application_id);
                }else {
                    student.setApplicationId(1000);
                }

                if( stId!= null){
                    int student_id = Integer.parseInt(studentRepo.getMaxStudentId()) + 1;
                    student.setStudentId(student_id + "");
                }else {
                    student.setStudentId("101");
                }

                studentRepo.save(student);
            }

            System.out.println(System.currentTimeMillis() - startTime);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Path fileToDelete = Paths.get(filePath);
            try {
                workbook.close();
                Files.delete(fileToDelete);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    String cellToString(Cell cell) {
        if (cell == null) {
            return "NA";
        }
        return cell.toString().trim();
    }

    Date cellToDate(Cell cell) throws Exception {
        Date date = null;
        if(cellToString(cell).equals("")){
            return null;
        }
        date = new SimpleDateFormat("dd-MMM-yyyy").parse(cell.toString().trim());
        //date = new SimpleDateFormat("dd/mm/yyyy").parse(cell.toString().trim());

        return date;
    }
}
