package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.request.TransferFormPayload;
import com.team1.it2hospitalk.model.response.FileDTO;
import com.team1.it2hospitalk.model.response.TransferFormDTO;
import com.team1.it2hospitalk.service.ITransferFormService;
import com.team1.it2hospitalk.service.impl.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping
@Validated
public class TransferFormController {

    private final ITransferFormService transferService;
    private final StorageService storageService;


    @PostMapping("/transfer-form")
    @PreAuthorize("hasAnyAuthority('USER')")
    @CacheEvict(value = "transfer-form", allEntries = true)
    public ResponseEntity<?> createTransferForm(Authentication auth,
                                                @Valid TransferFormPayload formDTO) {
        String username = auth.getName();
        TransferFormDTO dto = transferService.createTransferForm(username, formDTO);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/transfer-form")
    @PreAuthorize("hasAnyAuthority('USER', 'MANAGER')")
    @Cacheable(cacheNames = "transfer-form")
    public ResponseEntity<?> getCreatedTransferForm(Authentication auth) {
        String username = auth.getName();
        boolean isManager = auth.getAuthorities().contains(Role.MANAGER);

        // user get created transfer form
        List<TransferFormDTO> listOfDTOs;
        if (!isManager) {
            listOfDTOs = transferService.getCreatedTransferFromByUser(username);
        } else {
            // manager get received transfer form
            listOfDTOs = transferService.getReceivedTransferFromByUser(username);
        }

        // get accessible links for resource
        listOfDTOs.forEach(form -> {
            setAccessibleUrl(form.getMedicalSummary());
            setAccessibleUrl(form.getPatientProfile());
        });

        return ResponseEntity.ok(listOfDTOs);
    }

    @PutMapping("/transfer-form/{formID}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @CacheEvict(value = "transfer-form", allEntries = true)
    public ResponseEntity<?> approveTransferForm(@PathVariable Integer formID,
                                                 Authentication auth) {
        String managerUsername = auth.getName();
        transferService.approveTransferForm(formID, managerUsername);

        return ResponseEntity.noContent().build();
    }

    private void setAccessibleUrl(FileDTO fileDTO) {
        Duration expireTime = Duration.ofMinutes(30);

        String url = storageService.getAccessUrl(fileDTO.getFileName(), expireTime);
        fileDTO.setFileUrl(url);
    }

}