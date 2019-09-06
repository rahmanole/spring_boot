package com.minhaz.myapp.serviceImp;

import com.minhaz.myapp.entity.Email;
import com.minhaz.myapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ContactServiceImp implements ContactService {
    @Autowired
    JavaMailSender sender;
    String uploadedDir = System.getProperty("user.dir");

    public int sendMail(Email email){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String messageBody = "Visitor's Details"+"\n"+"Name:"+email.getName()+"\n"+
                "Company:"+email.getCompany()+"\n"+ "Email:"+email.getSender()+
                "\n\n"+email.getMessage();

        try {
            helper.setTo("rahmanole.bd@gmail.com");
            helper.setFrom(email.getSender());
            helper.setSubject(email.getSubject());
            helper.setText(messageBody);

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            return -1;
        }
        sender.send(message);
        return 1;
    }
}
