package app;

import app.ses.AmazonEmail;
import app.ses.Mail;
import app.ses.SESFrom;
import app.ses.SESProcessor;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import freemarker.template.Configuration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @Value("${app.velocity.templates.location}")
    private String basePackagePath;

    @Autowired
    private  SpringTemplateEngine templateEngine;

    @RequestMapping("/")
    public String index() throws IOException {
        AmazonEmail amazonEmail = new AmazonEmail(
                "eugene.s@it-dimension.com",
                "Hey Atta",
                "We have an offer for you :)");


        Mail mail = new Mail();
        mail.setFrom(SESFrom.NO_REPLY.getEmail());
        mail.setTo("eugene.s@it-dimension.com");
        mail.setSubject("Simple mail from aws cloud");
        Map<String, Object> model = new HashMap<>();
        model.put("templateVariable", "Simple mail with aws..");
        mail.setModel(model);

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process("email-template", context);


        //send Email using default NO_REPLY from email
        SESProcessor.getInstance().add(new AmazonEmail(
        "eugene.s@it-dimension.com",
        "Hey Atta***********", html));

//        //send Email using ATTA from email
//        SESProcessor.getInstance().add(new AmazonEmail(
//        "hi@attacomsian.com",
//        SESFrom.NO_REPLY,
//        "Hey Atta",
//        "We have an offer for you :)"));

        return "Emails Sent!";
    }




}
