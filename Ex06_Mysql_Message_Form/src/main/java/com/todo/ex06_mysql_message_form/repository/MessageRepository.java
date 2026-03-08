package com.todo.ex06_mysql_message_form.repository;

import com.todo.ex06_mysql_message_form.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
