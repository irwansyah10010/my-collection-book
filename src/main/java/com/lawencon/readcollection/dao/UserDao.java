package com.lawencon.readcollection.dao;

import com.lawencon.readcollection.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao extends BaseDao{

    public Optional<User> getByUsername(final String username) {
        final User result = getEM().createQuery("SELECT u FROM User u WHERE u.username = :username",User.class)
                .setParameter("username", username)
                .getSingleResult();

        final Optional<User> optional = Optional.ofNullable(result);

        return optional;
    }
}
