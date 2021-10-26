package com.team1.it2hospitalk.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends UserPersonalDTO {
    private String code;
    private String fullName;
    private Role role;
    private boolean isActive;
    private boolean isBlock;
    private Date createdAt;
    private Date updatedAt;
    private String hospitalName;

    public static UserDTO toUserDTO(User user) {
        if (user == null)
            return null;

        UserDTO userDTO = UserDTO.builder()
                .code(user.getId())
                .fullName(user.getFullName())
                .role(user.getRole())
                .isActive(user.getUsername() != null)
                .isBlock(user.isBlock())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .hospitalName(user.getWorkHospital().getName())
                .build();

        userDTO.setAvatarUrl(user.getAvatarUrl());
        userDTO.setJob(user.getJob());
        userDTO.setAge(user.getAge());
        userDTO.setAddress(userDTO.getAddress());
        userDTO.setPhoneNumber(userDTO.getPhoneNumber());

        return userDTO;
    }
}
