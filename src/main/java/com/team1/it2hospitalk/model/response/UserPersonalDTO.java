package com.team1.it2hospitalk.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPersonalDTO {
    private int age;
    private String job;
    private String phoneNumber;
    private String address;
    private String avatarUrl;
}
