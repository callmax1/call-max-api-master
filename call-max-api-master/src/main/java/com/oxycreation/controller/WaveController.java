package com.oxycreation.controller;

import com.oxycreation.dto.WaveDto;
import com.oxycreation.exception.ResourceNotFoundException;
import com.oxycreation.model.Wave;
import com.oxycreation.service.WaveService;
import com.oxycreation.service.UserDetailImpl;
import com.oxycreation.service.WaveService;
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
@RequestMapping(value = "/api/waves")
@CrossOrigin
public class WaveController {

    @Autowired
    private WaveService waveService;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @PostMapping
    public ResponseEntity<?> postWave(@Valid  @RequestBody WaveDto waveDto) {
        Wave wave = waveService.getByName(waveDto.getName());
        if (wave != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()),HttpServletResponse.SC_CONFLICT,"Conflict","this wave name already exists!","/api/waves"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        waveService.add(waveDto,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","wave added successfully","/api/waves"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putWave(@Valid @RequestBody WaveDto waveDto,@PathVariable(value = "id") Long id) {
        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        waveService.update(waveDto,id,userDetails.getId());
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK,"","wave updated successfully","/api/wavees"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wave> findById(@PathVariable(value = "id") Long id) {
        Wave data= waveService.getById(id);
        if(data == null){
            throw new ResourceNotFoundException("id: "+id);
        }
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/wave-name")
    public ResponseEntity<List<Wave>> findByWaveName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Wave>  data= waveService.findByWaveName(name);
        return new ResponseEntity<>(data,new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Pagination> waves(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                            @RequestParam(value = "search", defaultValue = "") String name,
                                            @RequestParam(value = "propertyName", defaultValue = "id") String propertyName,
                                            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder) {
        Pagination data = waveService.waves(new Page(pageIndex, pageSize), name, propertyName, sortOrder);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.OK);

    }

}
