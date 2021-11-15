package com.oxycreation.controller;

import com.oxycreation.enums.Status;
import com.oxycreation.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/status")
@CrossOrigin
public class StatusController {

    @GetMapping("/{id}")
    public ResponseEntity<String> findByStatus(@PathVariable(value = "id") Integer id) {
      String data = Status.Active.findOne(id).toString();
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }
}
