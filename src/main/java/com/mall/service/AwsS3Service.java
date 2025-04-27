package com.mall.service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface AwsS3Service {
    void putObject(String bucketName, String key, InputStream file, ObjectMetadata metadata);
}
