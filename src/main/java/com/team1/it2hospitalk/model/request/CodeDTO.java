package com.team1.it2hospitalk.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CodeDTO {

    @NotEmpty
    @Size(max = 45, message = "Name's length can't exceed 45 characters")
    private String name;

    @NotEmpty
    @Size(max = 255, message = "Location's length can't exceed 255 characters")
    private String location; // address

    @NotEmpty
    @Size(max = 45, message = "Job's length can't exceed 45 characters")
    private String department; // jobs

    private String position;
    private int hospitalId;
    private int channelId;
    private String code;
}
