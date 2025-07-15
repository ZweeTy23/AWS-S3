package org.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public FileService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
        return key;
    }

    public byte[] downloadFile(String key) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.getObjectAsBytes(getRequest).asByteArray();
    }

    public void deleteFile(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }

    public List<String> listFiles() {
        ListObjectsV2Response response = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build());

        return response.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }
}
