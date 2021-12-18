package com.team1.it2hospitalk.model.response;

import com.team1.it2hospitalk.model.entity.TransferForm;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferFormDTO {
    private int formId;
    private String title;
    private String patientProfile;
    private String medicalSummary;
    private String reason;
    private String doctorDiagnosis;
    private HospitalDTO toHospital;
    private HospitalDTO fromHospital;
    private String contact;
    private boolean isAccepted;

    public static TransferFormDTO toDTO(TransferForm form) {
        if (form == null)
            return null;

        HospitalDTO receiveHospital = HospitalDTO.toHospitalDTO(form.getReceiveHospital());
        HospitalDTO sendHospital = HospitalDTO.toHospitalDTO(form.getSendHospital());

        return TransferFormDTO.builder()
                .formId(form.getId())
                .title(form.getTitle())
                .doctorDiagnosis(form.getDoctorDiagnosis())
                .medicalSummary(form.getMedicalSummary())
                .patientProfile(form.getPatientInformation())
                .reason(form.getReason())
                .toHospital(receiveHospital)
                .fromHospital(sendHospital)
                .contact(form.getSendUser().getPhoneNumber())
                .isAccepted(form.isManagerAccepted())
                .build();
    }
}