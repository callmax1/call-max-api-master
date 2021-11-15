package com.oxycreation.controller;

import com.oxycreation.dto.VerticalDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Vertical;
import com.oxycreation.service.VerticalService;
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
@RequestMapping(value = "/api/verticals")
@CrossOrigin
public class VerticalController {

    @Autowired
    private VerticalService verticalService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postVertical(@Valid  @RequestBody VerticalDto verticalDto) {
        Vertical vertical = verticalService.getByName(verticalDto.getName());
        if (vertical != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this vertical name already exists!","/api/verticals"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        verticalService.add(verticalDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","vertical added successfully","/api/verticals"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCity(@Valid @RequestBody VerticalDto verticalDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        verticalService.update(verticalDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","vertical updated successfully","/api/verticals"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vertical> findById(@PathVariable(value = "id") Long id) {
        Vertical data= verticalService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/vertical-name")
    public ResponseEntity<List<Vertical>> findByVerticalName(@RequestParam(value = "name", defaultValue = "") String name) {

        List<Vertical>  data= verticalService.findByVerticalName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Pagination> verticals(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "name", defaultValue = "") String name) {
        Pagination data= verticalService.verticals(new Page(pageIndex,pageSize),name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

}
