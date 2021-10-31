package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.response.HospitalDTO;
import com.team1.it2hospitalk.service.IHospitalServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HospitalController {

    private final IHospitalServices hospitalServices;

    @GetMapping("/hospital")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getListOfHospital(
            @PageableDefault(sort = {"createdAt"},
                    direction = Sort.Direction.DESC,
                    size = 65535) Pageable pageable) {

        List<HospitalDTO> hospitalDTOList = hospitalServices.getListOfHospital(pageable);

        return ResponseEntity.ok(hospitalDTOList);
    }
}
