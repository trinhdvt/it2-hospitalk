package com.team1.it2hospitalk.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TransferFormPayload {
    @NotEmpty
    @Size(max = 255)
    private String title;

    private int toHospitalId;

    @NotEmpty
    @Size(max = 250)
    private String patientProfile;

    @NotEmpty
    @Size(max = 250)
    private String medicalSummary;

    @NotEmpty
    private String reason;

    @NotEmpty
    private String doctorDiagnosis;
}