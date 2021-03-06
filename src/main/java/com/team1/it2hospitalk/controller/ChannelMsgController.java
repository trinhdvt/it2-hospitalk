package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.service.IMsgServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Validated
public class ChannelMsgController {

    private final IMsgServices msgServices;

    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    @GetMapping("/room")
    @Cacheable("chat-room")
    public ResponseEntity<?> getAllMyChannel(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(msgServices.getAllMyChannel(username));

    }

    @GetMapping("/room/{roomID}/message")
    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    @Cacheable("chat-content")
    public ResponseEntity<?> getAllMessagesInRoom(
            @PathVariable("roomID") Integer channelId) {

        return ResponseEntity.ok(msgServices.getAllMessagesInRoom(channelId));
    }

    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    @PostMapping("/room/{roomID}/message")
    @CacheEvict(value = "chat-content", allEntries = true)
    public ResponseEntity<?> postNewMessage(Authentication auth,
                                            @PathVariable int roomID,
                                            @NotEmpty() String message) {

        String username = auth.getName();

        return ResponseEntity.ok(msgServices.createNewMessage(roomID, username, message));
    }

}