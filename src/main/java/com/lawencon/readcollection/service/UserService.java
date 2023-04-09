package com.lawencon.readcollection.service;

import com.lawencon.readcollection.dao.UserDao;
import com.lawencon.readcollection.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userInsert = userDao.save(user);

        Map<String, Object> message = new HashMap<>();

        if(userInsert != null){
            message.put("id", userInsert.getId());
            message.put("message", "user has been added");
        }

        return message;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.getByUsername(username);

        if(user.isPresent()){
            return new org.springframework.security.core.userdetails.User(username,user.get().getPassword(), new ArrayList<>());
        }

        throw new UsernameNotFoundException("Username dan Password salah");
    }

    public Optional<User> getByUsername(String email) {
        return userDao.getByUsername(email);
    }
}
