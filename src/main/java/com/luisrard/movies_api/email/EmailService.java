package com.luisrard.movies_api.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void send(String to, String email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirmation email");
            helper.setFrom(new InternetAddress("ac17300055@gmail.com", "Makala Vergazos"));
            mailSender.send(mimeMessage);

            LOGGER.info("Email sent");
        }catch (MessagingException | UnsupportedEncodingException e){
            LOGGER.info("Failed to send email ", e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
