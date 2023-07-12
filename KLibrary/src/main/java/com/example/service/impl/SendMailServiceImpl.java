package com.example.service.impl;

import com.example.domain.Mail;
import com.example.service.SendMailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(Mail mail) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(mail.getFrom()+"(kk)");
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContext(),true);

            //添加附件
            File f1 = new File(mail.getBookUrl());
            helper.addAttachment(f1.getName(),f1);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

















