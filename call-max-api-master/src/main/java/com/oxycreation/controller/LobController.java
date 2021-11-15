package com.oxycreation.controller;

import com.oxycreation.dto.LobDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Lob;
import com.oxycreation.service.LobService;
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
@RequestMapping(value = "/api/lobs")
@CrossOrigin
public class LobController {

    @Autowired
    private LobService lobService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postLob(@Valid  @RequestBody LobDto lobDto) {
        Lob lob = lobService.getByName(lobDto.getName());
        if (lob != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this lob name already exists!","/api/lobs"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        lobService.add(lobDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","lob added successfully","/api/lobs"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putLob(@Valid @RequestBody LobDto lobDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        lobService.update(lobDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","lob updated successfully","/api/lobes"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lob> findById(@PathVariable(value = "id") Long id) {
        Lob data= lobService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/lob-name")
    public ResponseEntity<List<Lob>> findByLobName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Lob>  data= lobService.findByLobName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/company-id")
    public ResponseEntity<List<Lob>> findByLobName(@RequestParam(value = "companyId") Long companyId,@RequestParam(value = "name", defaultValue = "") String name) {
        List<Lob>  data= lobService.findByCompanyId(companyId,name);
        if(data == null){
            throw new ResourceNotFoundException("id: "+companyId);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<Pagination> lobs(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "search", defaultValue = "") String name,
                                            @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {
        Pagination data = lobService.lobs(new Page(pageIndex, pageSize), name, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }

}
