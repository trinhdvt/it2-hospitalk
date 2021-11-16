package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.exception.NotFoundError;
import com.team1.it2hospitalk.repository.ResourceRepository;
import com.team1.it2hospitalk.service.impl.StorageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.Collections;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FileController {

    private final StorageService storageService;
    private final ResourceRepository resourceRepo;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = System.currentTimeMillis() + "_" + StringUtils.deleteWhitespace(file.getOriginalFilename());

        storageService.uploadFile(file, filename);
        return ResponseEntity.ok(Collections.singletonMap("fileName", filename));
    }

    @GetMapping("/{fileName}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAccessUrl(@PathVariable String fileName,
                                          Authentication auth) {

        String username = auth.getName();
        if (resourceRepo.existsByOwner_UsernameAndResourceName(username, fileName)) {
            Duration expireTime = Duration.ofMinutes(60);
            String url = storageService.getAccessUrl(fileName, expireTime);

            return ResponseEntity.ok(Collections.singletonMap("url", url));
        } else {
            throw new NotFoundError("File not found");
        }
    }

}
