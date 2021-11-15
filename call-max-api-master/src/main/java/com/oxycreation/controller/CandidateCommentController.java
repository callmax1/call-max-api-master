package com.oxycreation.controller;

import com.oxycreation.dto.CandidateCommentDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Candidate;
import com.oxycreation.model.CandidateComment;
import com.oxycreation.model.Lob;
import com.oxycreation.service.CandidateCommentService;
import com.oxycreation.service.UserDetailImpl;
import com.oxycreation.util.CommonResponse;
import com.oxycreation.util.DateFormats;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/candidate-comments")
@CrossOrigin
public class CandidateCommentController {

    @Autowired
    private CandidateCommentService candidateCommentService;


    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postCandidateComment(@Valid @RequestBody CandidateCommentDto candidateCommentDto) {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        candidateCommentService.add(candidateCommentDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","candidate comment added successfully","/api/candidate-comments"), new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CandidateComment> findById(@PathVariable(value = "id") Long id) {
        CandidateComment data= candidateCommentService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/candidate-id")
    public ResponseEntity<List<CandidateComment>> findByCandidateId(@RequestParam(value = "candidateId") Long candidateId) {
        List<CandidateComment>  data= candidateCommentService.getByCandidateId(candidateId);
        if(data == null){
            throw new ResourceNotFoundException("id: "+candidateId);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<Pagination> candidateComment(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                           @RequestParam(value = "search", defaultValue = "") String comment,
                                           @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                           @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {
        Pagination data = candidateCommentService.candidates(new Page(pageIndex, pageSize), comment, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }
}
