package com.oxycreation.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService {

    void uploadFileToS3Bucket(String folderName,MultipartFile multipartFile, boolean enablePublicReadAccess);

    byte[] downloadFile(String folderName, String fileName);

    void deleteFileFromS3Bucket(String folderName, String fileName);
}
