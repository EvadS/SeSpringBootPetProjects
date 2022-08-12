package com.example.filedemo.model;

import com.example.filedemo.constraint.ValidImage;
import org.springframework.web.multipart.MultipartFile;

public class ImageModel {
    private String imageName;

    private MultipartFile[] files;

}
