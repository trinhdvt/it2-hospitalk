package com.team1.it2hospitalk.model.response;

import com.team1.it2hospitalk.model.entity.SupportMsg;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SupportMsgDTO {
    private Integer id;
    private UserDTO creator;
    // private Channel channel;
    private String title;
    private String body;
    private int expectQuantity;
    private int currentQuantity;
    private Date createdAt;
    private Date closedAt;

    public static SupportMsgDTO toDTO(SupportMsg spMsg) {
        if (spMsg == null)
            return null;

        return SupportMsgDTO.builder()
                .id(spMsg.getId())
                .creator(UserDTO.toUserDTO(spMsg.getCreator()))
                .title(spMsg.getTitle())
                .body(spMsg.getBody())
                .expectQuantity(spMsg.getExpectQuantity())
                .currentQuantity(spMsg.getCurrentQuantity())
                .createdAt(spMsg.getCreatedAt())
                .closedAt(spMsg.getClosedAt())
                .build();
    }
}
