package com.yizhuoyan.teaching.demo.dao;

import com.yizhuoyan.teaching.demo.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 18/04/17.
 */
public interface UserDao extends JpaRepository<UserEntity,String> {

    default  UserEntity findByAccount(String account)throws  Exception{
        UserEntity exp=new UserEntity();
        exp.setAccount(account);
        return this.findOne(Example.of(exp)).orElse(null);
    }
}
