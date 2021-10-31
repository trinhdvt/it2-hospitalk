package com.team1.it2hospitalk.model.response;

import com.team1.it2hospitalk.model.entity.Hospital;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HospitalDTO {
    private int id;
    private String name;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    public static HospitalDTO toHospitalDTO(Hospital hospital) {
        if (hospital == null)
            return null;

        return HospitalDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .createdAt(hospital.getCreatedAt())
                .updatedAt(hospital.getUpdatedAt())
                .build();
    }
}
