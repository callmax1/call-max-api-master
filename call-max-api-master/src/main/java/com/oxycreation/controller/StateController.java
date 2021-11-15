package com.oxycreation.controller;

import com.oxycreation.dto.StateDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.State;
import com.oxycreation.service.StateService;
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
@RequestMapping(path = "/api/states")
@CrossOrigin
public class StateController {
    @Autowired
    private StateService stateService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;


    @PostMapping
    public ResponseEntity<?> postState(@Valid @RequestBody StateDto stateDto) {
        State state = stateService.getByName(stateDto.getName());
        if (state != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_CONFLICT,"Conflict","this state name already exists!","/api/companies"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateService.addState(stateDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","state added successfully","/api/states"), new HttpHeaders(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> putState(@Valid @RequestBody StateDto stateDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        stateService.updateState(stateDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","state updated successfully","/api/states"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable(value = "id") Long id) {
        State data= stateService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "state-name")
    public ResponseEntity<List<State>> findByName(@RequestParam String name) {
        List<State>  data= stateService.findByStateName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Pagination> states(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                                @RequestParam(value = "search", defaultValue = "") String name,
                                                @RequestParam(value="propertyName",defaultValue = "id") String propertyName,
                                                @RequestParam(value="sortOrder",defaultValue = "DESC") String sortOrder) {
        Pagination data= stateService.states(new Page(pageIndex,pageSize),name,propertyName,sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);
    }

}
