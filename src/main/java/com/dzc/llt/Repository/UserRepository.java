package com.dzc.llt.Repository;

import com.dzc.llt.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:dzc
 * @date 2021-01-04 10:08
 */

/**
 * user的dao
 */
public interface UserRepository extends JpaRepository<User,Long> {
    // 通过名字查找
    User findByName(String name);
    // 查找是否存在该用户
    boolean existsByName(String name);
}
