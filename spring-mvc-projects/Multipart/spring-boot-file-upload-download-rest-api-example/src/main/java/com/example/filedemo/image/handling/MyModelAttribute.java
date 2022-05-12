package com.example.filedemo.image.handling;

import org.springframework.web.multipart.MultipartFile;

public class MyModelAttribute {

    String array ;
   // @ContentType("application/pdf")
    @ContentType("image/JPEG")
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
