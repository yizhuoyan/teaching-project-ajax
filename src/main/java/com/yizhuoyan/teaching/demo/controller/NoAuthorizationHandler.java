package com.yizhuoyan.teaching.demo.controller;

import com.yizhuoyan.core.AjaxResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 18/04/17.
 */
@RestController
public class NoAuthorizationHandler {
    @RequestMapping("/noAuthorizaiton.do")
    public AjaxResponse hanler(@RequestAttribute String reason){
        if(reason==null){
            reason="无token或token无效";
        }
        return AjaxResponse.fail(reason);
    }
}
