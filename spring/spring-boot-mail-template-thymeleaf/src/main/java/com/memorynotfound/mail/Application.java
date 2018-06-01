package com.memorynotfound.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("Sending Email with Thymeleaf HTML Template Example");

        Mail mail = new Mail();
        mail.setFrom("litz-a@glodon.com");
        mail.setTo("litz-a@glodon.com");
        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

        Map<String, Object> model = new HashMap<>(3);
        model.put("name", "Memorynotfound.com");
        model.put("location", "Belgium");
        model.put("signature", "http://memorynotfound.com");
        mail.setModel(model);

        emailService.sendSimpleMessage(mail);
    }
}
