package com.team1.it2hospitalk.service;

import com.team1.it2hospitalk.model.response.HospitalDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHospitalServices {

    List<HospitalDTO> getListOfHospital(Pageable pageable);

}
