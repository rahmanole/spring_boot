package com.globalbookshop.gbs.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public interface FileUpload {
    boolean uploadFile(MultipartFile file, RedirectAttributes redirectAttributes);
}
