package com.lawencon.readcollection.business.status.controller;

import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.business.status.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<BaseResListDto<?>> getAll(){
        BaseResListDto<Map<String,Object>> baseResListDto = statusService.getAll();

        return new ResponseEntity<>(baseResListDto,HttpStatus.OK);
    }

}
