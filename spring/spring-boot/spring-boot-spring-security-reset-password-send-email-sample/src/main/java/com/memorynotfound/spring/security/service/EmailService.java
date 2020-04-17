package com.memorynotfound.spring.security.service;

import com.memorynotfound.spring.security.model.Mail;
import java.nio.charset.StandardCharsets;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * @author min
 */
@Service
public class EmailService {

  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private SpringTemplateEngine templateEngine;

  public void sendEmail(Mail mail) {
    try {
      MimeMessage message = emailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      Context context = new Context();
      context.setVariables(mail.getModel());
      String html = templateEngine.process("email/email-template", context);

      helper.setTo(mail.getTo());
      helper.setText(html, true);
      helper.setSubject(mail.getSubject());
      helper.setFrom(mail.getFrom());

      emailSender.send(message);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
