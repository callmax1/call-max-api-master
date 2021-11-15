package com.oxycreation.controller;


import com.oxycreation.dto.FileInfoDto;
import com.oxycreation.service.AmazonS3ClientService;
import com.oxycreation.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/aws-s3")
public class AwsFileHandlerController {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;


    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@RequestParam("bucketName") String bucketName, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            amazonS3ClientService.uploadFileToS3Bucket(bucketName, file, true);
            String filename =file.getOriginalFilename();
            String url = "https://call-max-documents.s3.ap-south-1.amazonaws.com/"+bucketName+"/"+file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new FileInfoDto(filename, url));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping(value= "/files")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) {
//        final byte[] data = amazonS3ClientService.downloadFile(bucketName,fileName);
//        final ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
//                .body(resource);

        byte[] fileEntity = amazonS3ClientService.downloadFile(bucketName,fileName);

        HttpHeaders header = new HttpHeaders();
        // header.setContentType(MediaType.valueOf("application/octet-stream"));
        header.setContentLength(fileEntity.length);
        header.set("Content-type", "application/octet-stream");
        header.set("Content-Disposition", "attachment; filename=" + fileName);

        return new ResponseEntity<>(fileEntity, header, HttpStatus.OK);
    }
    @DeleteMapping("files")
    public Map<String, String> deleteFile(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName)
    {
        System.out.println("Delete    "+fileName);
        amazonS3ClientService.deleteFileFromS3Bucket(bucketName,fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
}
