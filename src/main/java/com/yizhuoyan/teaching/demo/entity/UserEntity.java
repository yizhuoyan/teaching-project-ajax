package com.yizhuoyan.teaching.demo.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 18/04/16.
 */
@Entity
public class UserEntity  implements Serializable {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String account;
    private String name;
    private Instant lastLoginTime;
    private Instant createTime;
    private String lastLoginIp;
    private String password;



    public String getId() {
        return id;
    }

    public UserEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public UserEntity setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getLastLoginTime() {
        return lastLoginTime;
    }

    public UserEntity setLastLoginTime(Instant lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public UserEntity setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public UserEntity setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
