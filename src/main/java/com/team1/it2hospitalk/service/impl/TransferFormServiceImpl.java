package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.model.entity.Hospital;
import com.team1.it2hospitalk.model.entity.TransferForm;
import com.team1.it2hospitalk.model.entity.User;
import com.team1.it2hospitalk.model.request.TransferFormPayload;
import com.team1.it2hospitalk.model.response.TransferFormDTO;
import com.team1.it2hospitalk.repository.HospitalRepository;
import com.team1.it2hospitalk.repository.TransferFormRepository;
import com.team1.it2hospitalk.repository.UserRepository;
import com.team1.it2hospitalk.service.ITransferFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TransferFormServiceImpl implements ITransferFormService {

    private final UserRepository userRepo;
    private final HospitalRepository hospitalRepo;
    private final TransferFormRepository transferFormRepo;

    @Override
    public TransferFormDTO createTransferForm(String username, TransferFormPayload formDTO) {
        User sendUser = userRepo.findByUsername(username);
        Hospital sendHospital = sendUser.getWorkHospital();
        Hospital receiveHospital = hospitalRepo.findById(formDTO.getToHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        TransferForm form = TransferForm.builder()
                .sendUser(sendUser)
                .sendHospital(sendHospital)
                .receiveHospital(receiveHospital)
                .title(formDTO.getTitle())
                .patientInformation(formDTO.getPatientProfile())
                .medicalSummary(formDTO.getMedicalSummary())
                .reason(formDTO.getReason())
                .doctorDiagnosis(formDTO.getDoctorDiagnosis())
                .build();

        form = transferFormRepo.saveAndFlush(form);

        return TransferFormDTO.toDTO(form);
    }

    @Override
    public List<TransferFormDTO> getCreatedTransferFromByUser(String username) {
        List<TransferForm> transferFormList = transferFormRepo.getAllBySendUser_Username(username);

        return transferFormList.stream()
                .map(TransferFormDTO::toDTO)
                .collect(Collectors.toList());
    }
}