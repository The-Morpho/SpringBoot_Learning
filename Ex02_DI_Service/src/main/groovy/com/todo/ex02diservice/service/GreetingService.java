package com.todo.ex02diservice.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getHelloMessage() {
        return "Hello Spring Boot";
    }
}