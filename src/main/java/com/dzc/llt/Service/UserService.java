package com.dzc.llt.Service;

import com.dzc.llt.Pojo.User;
import com.dzc.llt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author:dzc
 * @date 2021-01-04 10:17
 */


public interface UserService {
    // 查找用户
    User findUer(String name);
    // 是否存在用户
    boolean existsUser(String name);
    // 保存用户
    void saveUser(User user);
}
