package com.oxycreation.controller;

import com.oxycreation.dto.*;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Company;
import com.oxycreation.service.UserDetailImpl;
import com.oxycreation.service.UserService;
import com.oxycreation.util.CommonResponse;
import com.oxycreation.util.DateFormats;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserService userService;


    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @GetMapping
    public ResponseEntity<Pagination> users(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "search", defaultValue = "") String email,
                                            @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Pagination data = userService.users(new Page(pageIndex, pageSize),userDetails.getId(), email, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(value = "id") Long id) {
        UserDto data = userService.getByUserId(id);
        if (data == null) {
            throw new ResourceNotFoundException("id: " + id);
        }
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> putUser(@Valid @RequestBody UserDto userDto, @PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.update(userDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","user updated successfully","/api/users"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/user-email")
    public ResponseEntity<List<UserDto>> findByCountryName(@RequestParam(value = "email", defaultValue = "") String email) {
        List<UserDto> data = userService.findByEmail(email);
        if (data == null) {
            throw new ResourceNotFoundException("email: " + email);
        }
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteByUserId(@PathVariable(value = "id") Long id) {
        UserDto data = userService.getByUserId(id);
        if (data == null) {
            throw new ResourceNotFoundException("id: " + id);
        }
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping(value = "/agent-name")
    public ResponseEntity<List<UserDto>> findByCompanyName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<UserDto>  data= userService.findByAgentName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }
}

