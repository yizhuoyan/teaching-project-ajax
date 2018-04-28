package com.yizhuoyan.teaching.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Administrator on 18/04/20.
 */
@Entity
public class SecurityLogEntity implements Serializable {

    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    //IP
    private String ip;
    //人物
    @JsonProperty("who")
    private String  occurWho;
    //时间
    @JsonProperty("when")
    private Instant occurWhen=Instant.now();
    //经过
    private String  doWhat;
    //结果
    private String  resultHow;
    //原因
    private String  resultWhy;

    public SecurityLogEntity(String doWhat) {
        this.doWhat = doWhat;
    }

    public SecurityLogEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOccurWho() {
        return occurWho;
    }

    public void setOccurWho(String occurWho) {
        this.occurWho = occurWho;
    }

    public Instant getOccurWhen() {
        return occurWhen;
    }

    public void setOccurWhen(Instant occurWhen) {
        this.occurWhen = occurWhen;
    }

    public String getDoWhat() {
        return doWhat;
    }

    public void setDoWhat(String doWhat) {
        this.doWhat = doWhat;
    }

    public String getResultHow() {
        return resultHow;
    }

    public void setResultHow(String resultHow) {
        this.resultHow = resultHow;
    }

    public String getResultWhy() {
        return resultWhy;
    }

    public void setResultWhy(String resultWhy) {
        this.resultWhy = resultWhy;
    }
    public void setResultWhy(Exception resultWhy) {
        this.resultWhy = resultWhy.toString();
    }
}
