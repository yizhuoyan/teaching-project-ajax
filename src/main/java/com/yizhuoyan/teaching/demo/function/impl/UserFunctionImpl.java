package com.yizhuoyan.teaching.demo.function.impl;

import com.yizhuoyan.core.PlatformException;
import com.yizhuoyan.core.UserContext;
import com.yizhuoyan.teaching.demo.controller.ao.ModifyPasswordAo;
import com.yizhuoyan.teaching.demo.controller.ao.UpdateUserAo;
import com.yizhuoyan.teaching.demo.controller.ao.UserAo;
import com.yizhuoyan.teaching.demo.dao.UserDao;
import com.yizhuoyan.teaching.demo.entity.UserEntity;
import com.yizhuoyan.teaching.demo.function.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.yizhuoyan.core.AssertThrowUtil.*;

/**
 * Created by Administrator on 18/04/17.
 */
@Transactional
@Service
public class UserFunctionImpl implements UserFunction {

    private final UserDao udao;

    @Autowired
    public UserFunctionImpl(UserDao udao) {
        this.udao=udao;
    }


    public UserContext login(String account, String password)throws Exception{
        account = assertNotBlank("账号不能为空", account);
        password = assertNotBlank("密码不能为空", password);
        UserEntity exp=new UserEntity();
        exp.setAccount(account);
        UserEntity u = udao.findOne(Example.of(exp)).orElseThrow(()->new PlatformException("账号不存在"));


        assertEquals("账号或密码不正确", u.getPassword(), password);


        UserContext uc = new UserContext();
        uc.setAccount(account);
        uc.setId(u.getId());
        uc.setLastLoginIp(u.getLastLoginIp());
        uc.setLastLoginTime(u.getLastLoginTime());
        uc.setName(u.getName());
        //update last login time
        u.setLastLoginTime(Instant.now());

        return uc;
    }

    @Override
    public boolean existAccount(String account) throws Exception {
        account = assertNotBlank("账号不能为空", account);
        UserEntity exp=new UserEntity();
        exp.setAccount(account);
        return udao.exists(Example.of(exp));
    }

    private static final Pattern ALL_NUMBER = Pattern.compile("\\d+");
    private static final Pattern ALL_ALPHABET = Pattern.compile("[a-zA-Z]+");

    @Override
    public void registerUser(UserAo ao) throws Exception {
        String account = assertNotBlank("账号不能为空", ao.getAccount());
        String name = assertNotBlank("名字不能为空", ao.getName());
        String password = assertNotBlank("密码不能为空", ao.getPassword());
        String passwordConfirm = assertNotBlank("密码确认不能为空", ao.getPasswordConfirm());

        //验证
        //account 5-16字符
        if (account.length() < 5 || account.length() > 16) {
            throw new PlatformException("账号长度必须大于5位小于16位");
        }
        //name 3-16
        if (name.length() < 3 || name.length() > 16) {
            throw new PlatformException("名字长度必须大于3位小于16位");
        }
        //password 6-16字母，数字 不能全数字或者字母
        if (password.length() < 6 || password.length() > 16) {
            throw new PlatformException("密码长度必须大于6位小于16位");
        }

        if (ALL_ALPHABET.matcher(password).matches() || ALL_NUMBER.matcher(password).matches()) {
            throw new PlatformException("密码不能全是字母或数字");
        }
        //两次密码一致
        if (!Objects.equals(password, passwordConfirm)) {
            throw new PlatformException("两次密码不一致");
        }
        //账号不能重复
        assertFalse("账号已存在",existAccount(account));
        UserEntity u = new UserEntity();
        u.setAccount(account);
        u.setCreateTime(Instant.now());
        u.setName(name);
        u.setPassword(password);
        udao.save(u);
    }

    @Override
    public UserEntity loadUser(String idOrAccount) throws Exception {
        return null;
    }

    @Override
    public void modifyPassword(ModifyPasswordAo ao) throws Exception {

        String account = assertNotBlank("账号不能为空", ao.getAccount());
        String oldPassword = assertNotBlank("旧密码不能为空", ao.getOldPassword());

        String newPassword = assertNotBlank("新密码不能为空", ao.getNewPassword());
        String newPasswordConfirm = assertNotBlank("新密码确认不能为空", ao.getNewPasswordConfirm());

        UserEntity u =udao.findByAccount(account);
        assertNotNull("账号输入错误", u);
        assertEquals("旧密码输入错误", u.getPassword(), oldPassword);
        assertEquals("两次新密码不一致", newPassword, newPasswordConfirm);
        assertNotEquals("新旧密码不能一样", newPassword, oldPassword);
        //password 6-16字母，数字 不能全数字或者字母
        if (newPassword.length() < 6 || newPassword.length() > 16) {
            throw new PlatformException("密码长度必须大于6位小于16位");
        }

        if (ALL_ALPHABET.matcher(newPassword).matches() || ALL_NUMBER.matcher(newPassword).matches()) {
            throw new PlatformException("密码不能全是字母或数字");
        }

        u.setPassword(newPassword);
    }

    @Override
    public void updateUser(UpdateUserAo ao) throws Exception {

        String id=assertNotBlank("id不能为空", ao.getId());

        String account = assertNotBlank("账号不能为空", ao.getAccount());

        String  name = assertNotBlank("名称不能为空", ao.getName());


        UserEntity oldUser=udao.findById(id).orElse(null);
        assertNotNull("数据不存在",oldUser);
        //account
        if (!Objects.equals(account, oldUser.getAccount())) {
            //account 5-16字符
            if (account.length() < 5 || account.length() > 16) {
                throw new PlatformException("账号长度必须大于5位小于16位");
            }
            //账号不能存在
            if (existAccount(account)) {
                throw new PlatformException("账号已存在");
            }

            oldUser.setAccount(account);

        }
        //name
        if (Objects.equals(name, oldUser.getName())) {
            //name 3-16
            if (name.length() < 3 || name.length() > 16) {
                throw new PlatformException("名字长度必须大于3位小于16位");
            }
            oldUser.setName(name);

        }
    }
}
