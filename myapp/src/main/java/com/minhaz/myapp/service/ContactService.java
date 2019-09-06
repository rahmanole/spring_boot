package com.minhaz.myapp.service;

import com.minhaz.myapp.entity.Email;
import org.springframework.stereotype.Service;


@Service
public interface ContactService {
    int sendMail(Email email);
}
