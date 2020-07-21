package com.wellsfargo.serv_req_center.auth.entity;

public class Student {

	private Integer studId;

	private String name;
	
	private String password;

	public Student() {
	}

	public Student(Integer studId, String name, String password) {
		super();
		this.studId = studId;
		this.name = name;
		this.password = password;
	}



	public Integer getStudId() {
		return studId;
	}

	public void setStudId(Integer studId) {
		this.studId = studId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
