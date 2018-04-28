package com.yizhuoyan.core;

import java.time.Instant;
import java.util.Date;

/**
 * 用户上下文
 * 包含用户的信息
 * @author yizhuoyan@hotmail.com
 *
 */
public class UserContext {

	private String id;
	private String account;
	private String name;
	private Instant lastLoginTime;
	private String lastLoginIp;
	private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Instant getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Instant lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
