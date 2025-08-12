package com.lawencon.readcollection.business.status.controller;

import com.lawencon.readcollection.base.dto.req.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.req.BaseResListDto;
import com.lawencon.readcollection.base.dto.req.BaseResSingleDto;
import com.lawencon.readcollection.business.status.service.StatusService;
import com.lawencon.readcollection.data.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<BaseResListDto<?>> getAll(){
        BaseResListDto<Map<String,Object>> baseResListDto = statusService.getAll();

        return new ResponseEntity<>(baseResListDto,HttpStatus.OK);
    }

    // Not use

    // @PostMapping
    // public ResponseEntity<BaseInsertResDto> save(@RequestBody List<Status> statusInsertReqDto){
    //     BaseInsertResDto baseInsertResDto = statusService.save(statusInsertReqDto);

    //     return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    // }

    // @GetMapping("{statusCode}/code")
    // public ResponseEntity<BaseResSingleDto<Status>> getByStatusCode(@PathVariable("statusCode") String statusCode){
    //     BaseResSingleDto<Status> baseResSingleDto = statusService.getByStatusCode(statusCode);

    //     return new ResponseEntity<BaseResSingleDto<Status>>(baseResSingleDto, HttpStatus.OK);
    // }
}
