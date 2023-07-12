package com.example.controller;

import com.example.domain.Message;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public void getMessage(Message message){
        messageService.receiveMessage(message.getId(),message.getMsg());
    }


    @GetMapping
    public String DoMessage(){
        return messageService.readMessage();
    }
}
