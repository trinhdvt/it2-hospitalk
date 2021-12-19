package com.team1.it2hospitalk.controller;

import com.team1.it2hospitalk.service.impl.StorageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FileController {

    private final StorageService storageService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = StringUtils.deleteWhitespace(file.getOriginalFilename());
        assert filename != null;
        String extension = filename.substring(filename.lastIndexOf("."));

        int ID_MAX_LENGTH = 8;
        String postfix = RandomStringUtils.randomAlphanumeric(ID_MAX_LENGTH);
        filename = filename.replace(extension, "") + "-" + postfix + extension;

        storageService.uploadFile(file, filename);
        return ResponseEntity.ok(Collections.singletonMap("fileUrl", filename));
    }
}