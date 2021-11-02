package com.team1.it2hospitalk.model.response;

import com.team1.it2hospitalk.model.entity.Channel;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ChannelDTO {
    private int id;
    private String title;
    private Date createdAt;
    private Date updatedAt;

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
