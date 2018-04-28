package com.yizhuoyan.teaching.demo.function;

import com.yizhuoyan.teaching.demo.core.PaginationQueryResult;
import com.yizhuoyan.teaching.demo.entity.SecurityLogEntity;

import java.util.Map;

/**
 * Created by Administrator on 18/04/20.
 */
public interface LogService {

    void log(SecurityLogEntity log)throws  Exception;

    SecurityLogEntity load(String id)throws  Exception;

    PaginationQueryResult find(String key,int pageNo,int pageSize)throws  Exception;


}
