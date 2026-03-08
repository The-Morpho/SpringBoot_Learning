package com.todo.ex06_mysql_message_form.controller;

import com.todo.ex06_mysql_message_form.repository.MessageRepository;
import com.todo.ex06_mysql_message_form.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "index"; // templates/index.html
    }

    @PostMapping("/messages")
    public String create(@RequestParam("content") String content) {
        if (content != null && !content.trim().isEmpty()) {
            messageRepository.save(new Message(content.trim()));
        }
        return "redirect:/";
    }
}
