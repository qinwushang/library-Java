package com.example.controller;

import com.example.controller.utils.R;
import com.example.domain.Mail;
import com.example.service.SendMailService;
import com.example.service.impl.SendMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class MailController {
    @Autowired
    private SendMailService service;

    @RequestMapping("/mail")
    @PostMapping
    public void send(Mail mail) {
        System.out.println("发送邮件成功:\n主题为："+ mail.getSubject()+"\n"+"内容为："+mail.getContext());
        System.out.println("收件人："+mail.getTo()+"\n"+"书籍url: "+mail.getBookUrl()+"\n");
//        service.sendMail(mail);
    }


    @RequestMapping("/ak")
    @PostMapping
    public void ret(String id) {
        System.out.println(id);
    }

}
