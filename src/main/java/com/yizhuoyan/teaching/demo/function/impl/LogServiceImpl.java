package com.yizhuoyan.teaching.demo.function.impl;

import com.yizhuoyan.teaching.demo.core.PaginationQueryResult;
import com.yizhuoyan.teaching.demo.dao.SecurityLogDao;
import com.yizhuoyan.teaching.demo.entity.SecurityLogEntity;
import com.yizhuoyan.teaching.demo.function.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.yizhuoyan.teaching.demo.core.ThisSystemUtil.$;

/**
 */
@Transactional
@Service
public class LogServiceImpl implements LogService {

    private final SecurityLogDao dao;
    @Autowired
    public LogServiceImpl(SecurityLogDao dao) {
        this.dao=dao;
    }

    @Override
    public void log(SecurityLogEntity log) throws Exception {
        dao.save(log);
    }

    @Override
    public SecurityLogEntity load(String id) throws Exception {

        return dao.findById(id).orElse(null);
    }

    @Override
    public PaginationQueryResult find(String key, int pageNo, int pageSize) throws Exception {
        if(pageNo<=0)pageNo=1;
        if(pageSize<=0)pageSize=10;
        key=$(key,null);
        Sort sort=Sort.by(Sort.Direction.DESC,"occurWhen");
        Pageable pageable=PageRequest.of(pageNo-1,pageSize,sort);
        Page<SecurityLogEntity> page;
        if(key==null){
             page=dao.findAll(pageable);
        }else{
             page=dao.findByKey(key,pageable);
        }
        PaginationQueryResult<SecurityLogEntity> result = new PaginationQueryResult<>();

        if(page.hasContent()){

            result.setRows(page.getContent());
        }
        result.setTotalRows(page.getTotalElements());
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);

        return result;
    }
}
