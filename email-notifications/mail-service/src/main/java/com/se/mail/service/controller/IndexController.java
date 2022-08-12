package com.se.mail.service.controller;

import com.se.mail.service.exception.MailSendException;
import com.se.mail.service.service.MailService;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;



@RestController
@Validated
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MailService mailService;

    @GetMapping(value = "/")
    public void index() {

        String recipientAddress = "eugene.s@it-dimension.com";
        String emailConfirmationUrl = "eugene.s@it-dimension.com";

        try {
            mailService.sendEmailVerification(emailConfirmationUrl, recipientAddress);
        } catch (IOException | MessagingException | TemplateException e) {
            logger.error(String.valueOf(e));
            throw new MailSendException(recipientAddress, "Email Verification");
        }
    }
}
