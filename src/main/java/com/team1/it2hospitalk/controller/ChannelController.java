package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.model.entity.Role;
import com.team1.it2hospitalk.model.response.ChannelDTO;
import com.team1.it2hospitalk.service.IChannelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChannelController {

    private final IChannelServices channelServices;

    @GetMapping("/channel")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> getAllChannels(
            @PageableDefault(sort = {"createdAt"},
                    direction = Direction.DESC,
                    size = 65535) Pageable pageable,
            Authentication auth) {

        boolean isAdmin = auth.getAuthorities().contains(Role.ADMIN);
        if (isAdmin) {
            List<ChannelDTO> channelDTOList = channelServices.getAllChannels(pageable);

            return ResponseEntity.ok(channelDTOList);

        } else {
            String username = auth.getName();
            List<ChannelDTO> channelDTOList = channelServices.getAllChannelCreatedBy(username, pageable);

            return ResponseEntity.ok(channelDTOList);
        }

    }

    @GetMapping("/hospital/{hospitalId}/channel")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getChannelOfHospital(
            @PathVariable("hospitalId") int hospitalId,
            @PageableDefault(sort = {"createdAt"},
                    direction = Direction.DESC,
                    size = 65535) Pageable pageable) {

        List<ChannelDTO> channelDTOList = channelServices.getAllChannels(hospitalId, pageable);

        return ResponseEntity.ok(channelDTOList);
    }
}
