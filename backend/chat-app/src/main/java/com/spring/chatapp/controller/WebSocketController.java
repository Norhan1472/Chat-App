package com.spring.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public String chatting(String messages){
       // System.out.println("Here");
        System.out.println(messages);
        return messages;
    }
}
