package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.request.TransferFormPayload;
import com.team1.it2hospitalk.model.response.TransferFormDTO;
import com.team1.it2hospitalk.service.ITransferFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping
@Validated
public class TransferFormController {

    private final ITransferFormService transferService;

    @PostMapping("/transfer-form")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> createTransferForm(Authentication auth,
                                                @Valid TransferFormPayload formDTO) {
        String username = auth.getName();
        TransferFormDTO dto = transferService.createTransferForm(username, formDTO);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/transfer-form")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getCreatedTransferForm(Authentication auth) {
        String username = auth.getName();
        List<TransferFormDTO> listOfDTOs = transferService.getCreatedTransferFromByUser(username);

        return ResponseEntity.ok(listOfDTOs);
    }


}