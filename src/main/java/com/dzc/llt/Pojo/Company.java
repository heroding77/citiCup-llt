package com.dzc.llt.Pojo;

import javax.persistence.*;

/**
 * @author:dzc
 * @date 2021-01-04 11:12
 */

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //属性
    private int id;
    private String name;
    private String password;

    /**
     * 有参构造
     * @param id
     * @param name
     * @param password
     */
    public Company(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * 无参构造
     */
    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
