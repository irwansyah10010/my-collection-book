package com.lawencon.readcollection.business.status.service;

import com.lawencon.readcollection.base.constant.Message;
import com.lawencon.readcollection.base.dto.req.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.req.BaseResListDto;
import com.lawencon.readcollection.base.dto.req.BaseResSingleDto;
import com.lawencon.readcollection.data.dao.StatusDao;
import com.lawencon.readcollection.data.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.transaction.Transactional;

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

    // public BaseResSingleDto<Status> getByStatusCode(String statusCode){
    //     BaseResSingleDto<Status> baseResSingleDto = new BaseResSingleDto<>();
        
    //     Status status = statusDao.getByStatusCode(statusCode);
        
    //     baseResSingleDto.setData(status);
        
    //     return baseResSingleDto;
    // }
    
    // @Transactional(rollbackOn = Exception.class)
    // public BaseInsertResDto save(List<Status> statuses){
    //     StringJoiner stringJoiner = new StringJoiner(" ");
        
    //     List<Object> listStatus = new ArrayList<>();
    
    //     statuses.forEach(status->{
    //         Status statusIn = new Status();
    
    //         statusIn.setStatusCode(status.getStatusCode());
    //         statusIn.setStatusName(status.getStatusName());
    
    //         Status statusInsert = statusDao.save(statusIn);
    
    //         if(statusInsert != null){
    //             stringJoiner.add(statusInsert.getId());
    //             listStatus.add(statusInsert);
    //         }
    //     });
    
    //     BaseInsertResDto baseInsertResDto = new BaseInsertResDto();
        
    //     if(statuses.size() == listStatus.size()){
    //         baseInsertResDto.setId(stringJoiner.toString());
    //         baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
    //     }else{
    //         statusDao.getEM().getTransaction().rollback();
    //         baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
    //     }
    
    //     return baseInsertResDto;
    // }

}
