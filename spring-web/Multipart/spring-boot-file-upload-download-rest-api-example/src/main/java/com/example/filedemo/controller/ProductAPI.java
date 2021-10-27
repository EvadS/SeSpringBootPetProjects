package com.example.filedemo.controller;


import com.example.filedemo.constraint.ProductIDExisting;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductAPI {
    @GetMapping("/{id}")
    public void  findById( @PathVariable @ProductIDExisting Long id) {
    int a =10;
    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam MultipartFile[] images, @RequestParam Integer[] numbers) {
        int a = 0;

        for(MultipartFile image: images ){
            long size = image.getSize();
            System.out.println("file name "+ image.getOriginalFilename());
            System.out.println("file name "+ image.getSize());

        }

        int aa =0;
    }
}
