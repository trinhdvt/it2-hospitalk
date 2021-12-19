package com.team1.it2hospitalk.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FileDTO {
    private String fileName;
    private String fileUrl;

    public FileDTO(String fileName) {
        this.fileName = fileName;
    }
}