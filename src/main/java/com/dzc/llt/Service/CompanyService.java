package com.dzc.llt.Service;

import com.dzc.llt.Pojo.Company;
import com.dzc.llt.Pojo.User;

import java.util.concurrent.CompletableFuture;

/**
 * @author:dzc
 * @date 2021-01-04 11:14
 */


public interface CompanyService {
    // 查找公司
    Company findCompany(String name);
    // 查找是否存在公司
    boolean existsCompany(String name);
    // 保存公司
    void saveCompany(Company company);
}
