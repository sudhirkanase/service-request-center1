package com.wellsfargo.serv_req_center.task_management.beans;

import java.io.Serializable;

public class Officer implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String officer;
	private Integer adminCode;
	private String name;
	private String email;
	
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	public Integer getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(Integer adminCode) {
		this.adminCode = adminCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
