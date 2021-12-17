package com.team1.it2hospitalk.repository;

import com.team1.it2hospitalk.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> getAllByChannel_Id(int channelId);

}