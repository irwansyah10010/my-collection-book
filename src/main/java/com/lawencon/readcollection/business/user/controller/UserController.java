package com.lawencon.readcollection.business.user.controller;

import com.lawencon.readcollection.business.user.service.UserService;
import com.lawencon.readcollection.data.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        Map<String,Object> map = userService.save(user);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
