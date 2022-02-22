package com.shed.aop.cglib.bean;

import org.springframework.stereotype.Component;

@Component("message")
public class Message {
    public void send(String str) {
        System.out.println("[MessageBean]发送消息: " + str);
    }
}
