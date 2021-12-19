package com.team1.it2hospitalk.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.team1.it2hospitalk.exception.HttpError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class StorageService {

    @Value("${cloud.aws.bucket}")
    private String fileBucket;

    private final AmazonS3 s3Client;

    @Async
    public void uploadFile(MultipartFile multipartFile, String fileName) {
        File uploadFile = convertToFile(multipartFile);

        PutObjectRequest objectRequest = new PutObjectRequest(fileBucket, fileName, uploadFile);
        s3Client.putObject(objectRequest);

        uploadFile.delete();
    }

    private File convertToFile(MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename() != null) {
            File convertedFile = new File(multipartFile.getOriginalFilename());

            try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(multipartFile.getBytes());
                return convertedFile;
            } catch (IOException ex) {
                throw new HttpError("Cannot upload this file: " + ex.getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else {
            throw new HttpError("File name is null", HttpStatus.BAD_REQUEST);
        }
    }

    public String getAccessUrl(String fileName, Duration expireTime) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expireTime.toMillis());
        GeneratePresignedUrlRequest urlGenerator = new GeneratePresignedUrlRequest(fileBucket, fileName)
                .withExpiration(expireDate)
                .withMethod(HttpMethod.GET);

        return s3Client.generatePresignedUrl(urlGenerator).toString();
    }

}