package com.oxycreation.controller;

import com.oxycreation.dto.CandidateDto;
import com.oxycreation.dto.FileInfoDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Candidate;
import com.oxycreation.service.AmazonS3ClientService;
import com.oxycreation.service.CandidateService;
import com.oxycreation.service.UserDetailImpl;
import com.oxycreation.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/candidates")
@CrossOrigin
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postCandidate(@Valid  @RequestBody CandidateDto candidateDto) {
        Candidate candidate = candidateService.getByEmail(candidateDto.getEmail());
        if (candidate != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this candidate name already exists!","/api/candidates"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        candidateService.add(candidateDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","candidate added successfully","/api/candidates"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCandidate(@Valid @RequestBody CandidateDto candidateDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        candidateService.update(candidateDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","candidate updated successfully","/api/candidates"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> findById(@PathVariable(value = "id") Long id) {
        Candidate data= candidateService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/candidate-email")
    public ResponseEntity<List<Candidate>> findByCandidateEmail(@RequestParam(value = "email", defaultValue = "") String email) {
        List<Candidate>  data= candidateService.findByCandidateEmail(email);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Pagination> candidates(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "search", defaultValue = "") String email,
                                            @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {
        Pagination data = candidateService.candidates(new Page(pageIndex, pageSize), email, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }

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

//    @GetMapping(value= "/files")
//    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName) {
//        final byte[] data = amazonS3ClientService.downloadFile(bucketName,fileName);
//        final ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
//    }

    @DeleteMapping("files")
    public Map<String, String> deleteFile(@RequestParam("bucketName") String bucketName,@RequestParam("fileName") String fileName)
    {
        System.out.println("Delete    "+fileName);
        amazonS3ClientService.deleteFileFromS3Bucket(bucketName,fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }





}
