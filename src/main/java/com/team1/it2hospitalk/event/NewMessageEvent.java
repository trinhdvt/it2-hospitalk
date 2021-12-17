package com.team1.it2hospitalk.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team1.it2hospitalk.model.entity.Message;
import com.team1.it2hospitalk.model.response.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.Date;

@Component
@Slf4j
public class NewMessageEvent {

    private static SimpMessagingTemplate messagingTemplate;
    private static ObjectMapper jacksonMapper;
    private static final String brokerPrefix = "/topic/message";

    @Autowired
    public void init(SimpMessagingTemplate messagingTemplate, ObjectMapper jacksonMapper) {
        NewMessageEvent.jacksonMapper = jacksonMapper;
        NewMessageEvent.messagingTemplate = messagingTemplate;
    }

    @PrePersist
    void prePersist(Message msg) {
        msg.setCreatedAt(new Date());
    }

    @PostPersist
    public void postPersist(Message msg) throws JsonProcessingException {
        int channelId = msg.getChannel().getId();
        MessageDTO dto = MessageDTO.from(msg);
        String dest = brokerPrefix + "/" + channelId;

        log.debug("Sending msg to: {}", dest);
        messagingTemplate.convertAndSend(dest, jacksonMapper.writeValueAsString(dto));
    }
}