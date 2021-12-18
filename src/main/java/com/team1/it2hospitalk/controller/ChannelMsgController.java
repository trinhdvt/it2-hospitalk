package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.service.IMsgServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ChannelMsgController {

    private final IMsgServices msgServices;

    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    @GetMapping("/room")
    public ResponseEntity<?> getAllMyChannel(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(msgServices.getAllMyChannel(username));

    }

    @GetMapping("/room/{roomID}/message")
    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    public ResponseEntity<?> getAllMessagesInRoom(
            @PathVariable("roomID") Integer channelId) {

        return ResponseEntity.ok(msgServices.getAllMessagesInRoom(channelId));
    }

    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    @PostMapping("/room/{roomID}/message")
    public ResponseEntity<?> postNewMessage(Authentication auth,
                                            @PathVariable int roomID,
                                            String message) {

        String username = auth.getName();

        return ResponseEntity.ok(msgServices.createNewMessage(roomID, username, message));
    }

}