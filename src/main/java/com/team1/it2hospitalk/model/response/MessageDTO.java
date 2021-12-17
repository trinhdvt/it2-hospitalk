package com.team1.it2hospitalk.model.response;

import com.team1.it2hospitalk.model.entity.Message;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Data
@Builder
public class MessageDTO {
    private Integer messageId;
    private UserDTO sender;
    private Integer channelId;
    private String body;
    private Date createdAt;

    public static MessageDTO from(Message message) {
        return MessageDTO.builder()
                .sender(UserDTO.toUserDTO(message.getCreator()))
                .channelId(message.getChannel().getId())
                .messageId(message.getId())
                .body(message.getBody())
                .createdAt(message.getCreatedAt())
                .build();
    }
}