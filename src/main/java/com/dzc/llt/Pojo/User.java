package com.dzc.llt.Pojo;

import javax.persistence.*;

/**
 * @author:dzc
 * @date 2021-01-04 9:41
 */

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //属性
    private int id;
    private String name;
    private String password;
    private String email;

    /**
     * 有参构造
     * @param name
     * @param password
     * @param email
     */
    public User(String name, String password, String email){
        this.name=name;
        this.password=password;
        this.email=email;
    }

    /**
     * 有参构造
     * @param id
     * @param name
     * @param password
     * @param email
     */
    public User(int id, String name, String password, String email){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
    }

    /**
     * 无参构造
     */
    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
