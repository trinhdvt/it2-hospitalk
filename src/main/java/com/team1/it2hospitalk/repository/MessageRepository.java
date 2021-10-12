package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
