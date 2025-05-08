package com.pet.common.storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class StorageServiceS3Impl implements StorageService {

    private S3Client s3Client;
    private final S3Config s3Config;

    @PostConstruct
    public void init() {
        s3Client = S3Client.builder()
                .region(Region.of(s3Config.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(s3Config.getAccessKey(), s3Config.getSecretKey())
                ))
                .build();
    }

    @Override
    public void upload(String path, String filename, InputStream inputStream) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(path + "/" + filename)
                .build();

        s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, -1));
    }

    @Override
    public InputStream download(String path, String filename) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(path + "/" + filename)
                .build();

        return s3Client.getObject(request);
    }

    @Override
    public void delete(String path, String filename) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(path + "/" + filename)
                .build();

        s3Client.deleteObject(request);
    }

    @Override
    public String getFileUrl(String path, String filename) {
        return "https://" + s3Config.getBucket() + ".s3." + s3Config.getRegion() + ".amazonaws.com/" + path + "/" + filename;
    }
}
