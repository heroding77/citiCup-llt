package com.dzc.llt.Repository;

import com.dzc.llt.Pojo.Company;
import com.dzc.llt.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:dzc
 * @date 2021-01-04 11:16
 */


public interface CompanyRepository extends JpaRepository<Company,Long> {
    // 通过名字查找
    Company findByName(String name);
    // 查找是否存在该公司
    boolean existsByName(String name);
}
