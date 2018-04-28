package com.yizhuoyan.teaching.demo.dao;

import com.yizhuoyan.teaching.demo.entity.SecurityLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 18/04/20.
 */
public interface SecurityLogDao  extends JpaRepository<SecurityLogEntity,String>,JpaSpecificationExecutor<SecurityLogEntity>{

    @Query(value = "select t from #{#entityName} t  where t.ip like %?1% or t.doWhat like %?1%")
    Page<SecurityLogEntity> findByKey(String key,Pageable pageable)throws Exception;
}
