package com.team1.it2hospitalk.service.impl;

import com.team1.it2hospitalk.model.response.HospitalDTO;
import com.team1.it2hospitalk.repository.HospitalRepository;
import com.team1.it2hospitalk.service.IHospitalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HospitalServicesImpl implements IHospitalServices {

    private final HospitalRepository hospitalRepo;


    @Override
    public List<HospitalDTO> getListOfHospital(Pageable pageable) {
        return hospitalRepo.findAll(pageable)
                .map(HospitalDTO::toHospitalDTO)
                .getContent();
    }
}
