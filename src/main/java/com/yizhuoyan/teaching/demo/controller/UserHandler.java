package com.yizhuoyan.teaching.demo.controller;

import com.yizhuoyan.core.AjaxResponse;
import com.yizhuoyan.core.PlatformException;
import com.yizhuoyan.core.UserContext;
import com.yizhuoyan.teaching.demo.controller.ao.ModifyPasswordAo;
import com.yizhuoyan.teaching.demo.controller.ao.UpdateUserAo;
import com.yizhuoyan.teaching.demo.controller.ao.UserAo;
import com.yizhuoyan.teaching.demo.core.EncrypDES;
import com.yizhuoyan.teaching.demo.entity.SecurityLogEntity;
import com.yizhuoyan.teaching.demo.entity.UserEntity;
import com.yizhuoyan.teaching.demo.function.LogService;
import com.yizhuoyan.teaching.demo.function.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.yizhuoyan.core.AssertThrowUtil.*;

/**
 * Created by Administrator on 18/04/16.
 */
@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFunction fun;
    @Autowired
    private LogService logService;


    @RequestMapping("/login.do")
    public AjaxResponse login(String account, String password, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        SecurityLogEntity log=new SecurityLogEntity();
        try {
            UserContext uc = fun.login(account, password);
            //生成token
            String token= EncrypDES.encrytor(account);
            uc.setToken(token);
            log.setResultHow("登陆成功!");
            return AjaxResponse.ok(uc);

        } catch (PlatformException e) {
            log.setResultHow("登陆失败");
            log.setResultWhy(e);
            return AjaxResponse.fail(e);
        }finally {
            log.setIp(req.getRemoteAddr());
            log.setOccurWho(account);
            log.setDoWhat(String.format("使用%s尝试登陆",account));
            logService.log(log);
        }
    }

    @RequestMapping("/logout.do")
    public AjaxResponse logout(HttpServletRequest req) throws Throwable {
        SecurityLogEntity log=new SecurityLogEntity();
        try {

            String account=(String)req.getAttribute("account");
            log.setOccurWho(account);
            req.getSession().invalidate();
            return AjaxResponse.ok();
        } catch (PlatformException e) {
            return AjaxResponse.fail(e);
        }finally {
            log.setIp(req.getRemoteAddr());
            log.setDoWhat("注销");
            logService.log(log);
        }
    }


    @PostMapping("/register.do")
    public AjaxResponse register(UserAo ao,HttpServletRequest req) throws Throwable {
        SecurityLogEntity log=new SecurityLogEntity();
        try {
            log.setOccurWho(ao.getAccount());
            fun.registerUser(ao);
            log.setResultHow("注册成功");
            return AjaxResponse.ok();

        } catch (PlatformException e) {
            log.setResultHow("注册失败");
            log.setResultWhy(e);
            return AjaxResponse.fail(e);
        }finally {
            log.setIp(req.getRemoteAddr());
            log.setDoWhat("注册");
            logService.log(log);
        }

    }

    @PostMapping("/modifyPassword.do")
    public AjaxResponse modifyPassword(ModifyPasswordAo ao,HttpServletRequest req) throws Throwable {
        SecurityLogEntity log=new SecurityLogEntity();
        log.setOccurWho(ao.getAccount());
        try {

            fun.modifyPassword(ao);
            log.setResultHow("成功");
            return AjaxResponse.ok();

        } catch (PlatformException e) {
            log.setResultHow("失败");
            log.setResultWhy(e);
            return AjaxResponse.fail(e);
        }finally {
            log.setIp(req.getRemoteAddr());
            log.setDoWhat("修改密码");
            log.setOccurWho(ao.getAccount());
            logService.log(log);
        }
    }

    @RequestMapping("/existAccount.do")
    public AjaxResponse existAccount(String account) throws Throwable {
        try {

            boolean result=fun.existAccount(account);
            return AjaxResponse.ok(result);

        } catch (PlatformException e) {
            return AjaxResponse.fail(e);
        }
    }


    @PostMapping("/modify.do")
    public AjaxResponse modify(UpdateUserAo ao,HttpServletRequest req) throws Throwable {
        SecurityLogEntity log=new SecurityLogEntity();
        log.setOccurWho(ao.getAccount());
        try {

            fun.updateUser(ao);
            log.setResultHow("成功");
            return AjaxResponse.ok();

        } catch (PlatformException e) {
            log.setResultHow("失败");
            log.setResultWhy(e);
            return AjaxResponse.fail(e);
        }finally {
            log.setIp(req.getRemoteAddr());
            log.setDoWhat("修改个人信息");
            log.setOccurWho(ao.getAccount());
            logService.log(log);
        }
    }

}

