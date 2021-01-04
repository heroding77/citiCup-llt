package com.dzc.llt.Service.Impl;

import com.dzc.llt.Pojo.User;
import com.dzc.llt.Repository.UserRepository;
import com.dzc.llt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:dzc
 * @date 2021-01-04 10:27
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUer(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean existsUser(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
