package com.team1.it2hospitalk.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team1.it2hospitalk.model.entity.Channel;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private int id;
    private String title;
    private Date createdAt;
    private Date updatedAt;
    private List<MessageDTO> messages;

    public static ChannelDTO toChannelDTO(Channel channel) {
        if (channel == null) {
            return null;
        }

        return ChannelDTO.builder()
                .id(channel.getId())
                .title(channel.getTitle())
                .createdAt(channel.getCreatedAt())
                .updatedAt(channel.getUpdatedAt())
                .build();
    }
}