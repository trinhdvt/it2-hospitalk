package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.response.SupportMsgDTO;
import com.team1.it2hospitalk.service.ISpMsgServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Validated
public class SpMsgController {

    private final ISpMsgServices spMsgServices;

    // TODO: Allow manager to get all created help
    @RequestMapping("/help")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getListOfUser(@PageableDefault(sort = {"createdAt"},
            direction = Direction.DESC) Pageable pageable) {

        List<SupportMsgDTO> listOfSpMsg = spMsgServices.getListOfSpMsg(pageable);

        return ResponseEntity.ok(listOfSpMsg);
    }


    @GetMapping("/hospital/{hospitalId}/help")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getHelpOfHospital(
            @PathVariable("hospitalId") int hospitalId,
            @PageableDefault(sort = {"createdAt"},
                    direction = Direction.DESC,
                    size = 65535) Pageable pageable) {

        List<SupportMsgDTO> listOfSpMsg = spMsgServices.getListOfSpMsg(hospitalId, pageable);

        return ResponseEntity.ok(listOfSpMsg);
    }
}
