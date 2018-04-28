package com.yizhuoyan.teaching.demo.function;

import com.yizhuoyan.core.UserContext;
import com.yizhuoyan.teaching.demo.controller.ao.ModifyPasswordAo;
import com.yizhuoyan.teaching.demo.controller.ao.UpdateUserAo;
import com.yizhuoyan.teaching.demo.controller.ao.UserAo;
import com.yizhuoyan.teaching.demo.entity.UserEntity;

import java.util.Map;

/**
 * Created by Administrator on 18/04/17.
 */
public interface UserFunction {
    UserContext login(String account, String password)throws Exception;

    void registerUser(UserAo u)throws  Exception;

    boolean existAccount(String account)throws  Exception;


    UserEntity loadUser(String idOrAccount)throws  Exception;

    void updateUser(UpdateUserAo ao)throws Exception;

    void modifyPassword(ModifyPasswordAo ao)throws  Exception;






}
