package com.oxycreation.controller;

import com.oxycreation.dto.RoleDto;
import com.oxycreation.dto.UserDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Role;
import com.oxycreation.service.RoleService;
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
@RequestMapping(value = "/api/roles")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postRole(@Valid  @RequestBody RoleDto roleDto) {
        Role role = roleService.getByName(roleDto.getName());
        if (role != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this role name already exists!","/api/roles"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        roleService.addRole(roleDto);
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","role added successfully","/api/roles"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCity(@Valid @RequestBody RoleDto roleDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        roleService.updateRole(roleDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","role updated successfully","/api/roles"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable(value = "id") Long id) {
        Role data= roleService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/role-name")
    public ResponseEntity<List<Role>> findByRoleName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Role>  data= roleService.findByRoleName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Pagination> roles(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "name", defaultValue = "") String name) {
        Pagination data= roleService.roles(new Page(pageIndex,pageSize),name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

}
