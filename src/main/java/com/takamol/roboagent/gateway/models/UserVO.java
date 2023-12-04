package com.takamol.roboagent.gateway.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserVO {
	@Id
	private String userName;

	private String email;
	private String name;
	private boolean isAdmin;

	private transient List<AuthoritiesVO> authorities = new ArrayList<AuthoritiesVO>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public List<AuthoritiesVO> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthoritiesVO> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserVO [userName=" + userName + ", email=" + email + ", name=" + name + ", authorities=" + authorities
				+ "]";
	}

}