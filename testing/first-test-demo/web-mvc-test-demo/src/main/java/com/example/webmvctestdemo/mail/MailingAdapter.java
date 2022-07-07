package com.example.webmvctestdemo.mail;

import com.example.webmvctestdemo.domain.SendMailPort;
import org.springframework.stereotype.Component;

@Component
public class MailingAdapter implements SendMailPort {

    @Override
    public void sendMail(String subject, String text) {
        // sending a mail...
    }
}
