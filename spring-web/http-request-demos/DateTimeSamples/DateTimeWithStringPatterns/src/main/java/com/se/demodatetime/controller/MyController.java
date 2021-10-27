package com.se.demodatetime.controller;

import com.se.demodatetime.model.MyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/myexample")
public class MyController {

    @GetMapping("{id}")
    public ResponseEntity<MyDto> findById(@PathVariable Long id) {

        MyDto myDto = new MyDto("name", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        return ResponseEntity.ok(myDto);
    }
}