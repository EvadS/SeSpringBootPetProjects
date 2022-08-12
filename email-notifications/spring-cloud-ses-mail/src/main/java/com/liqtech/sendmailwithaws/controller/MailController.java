package com.liqtech.sendmailwithaws.controller;

import com.liqtech.sendmailwithaws.config.CustomPropertyConfig;
import com.liqtech.sendmailwithaws.Mail;
import com.liqtech.sendmailwithaws.service.EmailSenderService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	private EmailSenderService emailSenderService;
	private CustomPropertyConfig customPropertyConfig;

	public MailController(EmailSenderService emailSenderService, CustomPropertyConfig customPropertyConfig) {
		this.emailSenderService = emailSenderService;
		this.customPropertyConfig = customPropertyConfig;
	}

	@GetMapping(value = "/{email}")
	public String sendMail(@PathVariable(value = "email") String recipient) throws MessagingException {
		Mail mail = getMail(recipient);
		emailSenderService.sendEmail(mail);
		return "Check your email";

	}

	private Mail getMail(String recipient) {
		Mail mail = new Mail();
		mail.setFrom(customPropertyConfig.mailFrom);
		mail.setTo(recipient);
		mail.setSubject("Simple mail from aws cloud");
		Map<String, Object> model = new HashMap<>();
		model.put("templateVariable", "Simple mail with aws..");
		mail.setModel(model);
		return mail;
	}
}
