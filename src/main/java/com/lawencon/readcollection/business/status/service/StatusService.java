package com.lawencon.readcollection.business.status.service;

import com.lawencon.readcollection.base.dto.req.BaseResListDto;
import com.lawencon.readcollection.data.dao.StatusDao;
import com.lawencon.readcollection.data.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatusService {

    @Autowired
    private StatusDao statusDao;

    public BaseResListDto<Map<String,Object>> getAll(){
        List<Map<String,Object>> all = statusDao.findAll();

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(statusDao.count(Status.class));

        return baseResListDto;
    }

}
