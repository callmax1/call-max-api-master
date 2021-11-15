package com.oxycreation.controller;

import com.oxycreation.dto.CompanyDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Company;
import com.oxycreation.service.CompanyService;
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
@RequestMapping(value = "/api/companies")
@CrossOrigin
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postCompany(@Valid  @RequestBody CompanyDto companyDto) {
        Company company = companyService.getByName(companyDto.getName());
        if (company != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this company name already exists!","/api/companies"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        companyService.add(companyDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","company added successfully","/api/companies"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCompany(@Valid @RequestBody CompanyDto companyDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        companyService.update(companyDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","company updated successfully","/api/companies"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable(value = "id") Long id) {
        Company data= companyService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/company-name")
    public ResponseEntity<List<Company>> findByCompanyName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Company>  data= companyService.findByCompanyName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Pagination> companies(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "search", defaultValue = "") String name,
                                            @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {
        Pagination data = companyService.companies(new Page(pageIndex, pageSize), name, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }



}
