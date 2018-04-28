package com.yizhuoyan.teaching.demo.controller;

import com.yizhuoyan.core.AjaxResponse;
import com.yizhuoyan.core.PlatformException;
import com.yizhuoyan.core.UserContext;
import com.yizhuoyan.teaching.demo.controller.ao.ModifyPasswordAo;
import com.yizhuoyan.teaching.demo.controller.ao.UpdateUserAo;
import com.yizhuoyan.teaching.demo.controller.ao.UserAo;
import com.yizhuoyan.teaching.demo.core.EncrypDES;
import com.yizhuoyan.teaching.demo.core.PaginationQueryResult;
import com.yizhuoyan.teaching.demo.core.ThisSystemUtil;
import com.yizhuoyan.teaching.demo.entity.SecurityLogEntity;
import com.yizhuoyan.teaching.demo.function.LogService;
import com.yizhuoyan.teaching.demo.function.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 18/04/16.
 */
@RestController
@RequestMapping("/log")
public class LogHandler {

    @Autowired
    private LogService logService;


    @PostMapping("/list.do")
    public AjaxResponse list(String key,String pageNo,String pageSize) throws Throwable {
        try {
            int pageNoInt= ThisSystemUtil.parseInt(pageNo,-1);
            int pageSiseInt=ThisSystemUtil.parseInt(pageSize,-1);

            PaginationQueryResult<SecurityLogEntity> result= logService.find(key,pageNoInt,pageSiseInt);

            return AjaxResponse.ok(result);

        } catch (PlatformException e) {
            return AjaxResponse.fail(e);
        }finally {
        }
    }

}

