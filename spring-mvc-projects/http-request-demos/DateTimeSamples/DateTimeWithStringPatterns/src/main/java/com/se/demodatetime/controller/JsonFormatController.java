package com.se.demodatetime.controller;

import com.se.demodatetime.model.Contact;
import com.se.demodatetime.model.ContactWithJavaUtilDate;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "JsonFormatController", description = "Functionality for demonstrate json formatting")
@RestController
@RequestMapping("/json-formatter/")
public class JsonFormatController {

    @GetMapping("/test-Contact")
    public Contact getContact() {
        return new Contact();
    }

    @GetMapping("/test-ContactWithJavaUtilDate")
    public ContactWithJavaUtilDate getContactWithJavaUtilDatet() {
        return new ContactWithJavaUtilDate();
    }
}


