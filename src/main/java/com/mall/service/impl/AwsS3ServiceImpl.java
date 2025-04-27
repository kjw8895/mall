package com.mall.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mall.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsS3ServiceImpl implements AwsS3Service {
    private final AmazonS3 amazonS3;

    @Override
    public void putObject(String bucketName, String key, InputStream file, ObjectMetadata metadata) {
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file, metadata));
        } catch (Exception e) {
            log.error("error : upload object failed", e);
            throw e;
        }
    }
}
