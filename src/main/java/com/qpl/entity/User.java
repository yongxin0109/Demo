package com.qpl.entity;

public class User {

	private Integer uid;
	private String username;
	private String password;
	private String email;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public boolean isAllNull(){
		if(uid!=null&&uid!=0){
			return false;
		}
		if(username!=null&&username!=""){
			return false;
		}
		if(password!=null&&password!=""){
			return false;
		}
		if(email!=null&&email!=""){
			return false;
		}
		return true;
	}
}
