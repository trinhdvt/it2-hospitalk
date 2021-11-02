package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.model.response.SupportMsgDTO;
import com.team1.it2hospitalk.repository.SupportMsgRepo;
import com.team1.it2hospitalk.service.ISpMsgServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SpMsgServicesImpl implements ISpMsgServices {

    private final SupportMsgRepo supportMsgRepo;

    @Override
    public List<SupportMsgDTO> getListOfSpMsg(Pageable pageable) {
        return supportMsgRepo.findAll(pageable)
                .map(SupportMsgDTO::toDTO)
                .getContent();
    }

    @Override
    public List<SupportMsgDTO> getListOfSpMsg(int hospitalId, Pageable pageable) {
        return supportMsgRepo.getAllByCreator_ManageHospital_Id(hospitalId, pageable)
                .map(SupportMsgDTO::toDTO)
                .getContent();
    }
}
