package com.se.sample.controller;


import com.amazonaws.services.rekognition.model.FaceDetail;
import com.se.sample.model.Avatar;
import com.se.sample.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping("/uploadFile")
    public FaceDetail uploadFile(@RequestPart(value = "file") MultipartFile photo) {
        return this.avatarService.generateAvatar(photo);
    }


    @PostMapping("/detect-label")
    public void detectLabels(@RequestPart(value = "file") MultipartFile photo) {
        this.avatarService.awsDetectLabels(photo);
    }


    @GetMapping("/list/{id}")
    public List<String> getList(@PathVariable String id) {
        return this.avatarService.getList(id);
    }
}

