package com.yizhuoyan.teaching.demo.controller;

import com.yizhuoyan.core.AjaxResponse;
import com.yizhuoyan.core.AssertThrowUtil;
import com.yizhuoyan.core.PlatformException;
import com.yizhuoyan.core.ThisSystemUtil;
import com.yizhuoyan.teaching.demo.core.RMBUpper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by Administrator on 18/04/17.
 */
@RestController
@RequestMapping("/simple")
public class SimpleAjaxHandler {

    @GetMapping("/upper.do")
    public AjaxResponse upperString(String target){
        try {
            if (ThisSystemUtil.isBlank(target)) {
                throw new PlatformException("target必须填写");
            }
            target = target.trim();
            return AjaxResponse.ok(target.toUpperCase());
        }catch(PlatformException e){
            return AjaxResponse.fail(e);
        }
    }

    @GetMapping("/rmbUpper.do")
    public AjaxResponse rmbUpper(String rmb){
        try {
            if (ThisSystemUtil.isBlank(rmb)) {
                throw new PlatformException("rmb必须填写");
            }

            String result=RMBUpper.convertCurrency(rmb);
            return AjaxResponse.ok(result);
        }catch(PlatformException|IllegalArgumentException e){
            return AjaxResponse.fail(e);
        }
    }

    @RequestMapping("/sum.do")
    public AjaxResponse sum(String a,String b){
        try {
            a= AssertThrowUtil.assertNotBlank("a不能为空",a);
            b= AssertThrowUtil.assertNotBlank("b不能为空",b);

            BigDecimal aBig,bBig;

            try {
                aBig=new BigDecimal(a);
            }catch(Exception e){
                throw  new PlatformException("a数字格式错误");
            }
            try {
                bBig=new BigDecimal(b);
            }catch(Exception e){

                throw  new PlatformException("b数字格式错误");
            }
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
            return AjaxResponse.ok(aBig.add(bBig).toPlainString());
        }catch(PlatformException e){
            return AjaxResponse.fail(e);
        }
    }



}
