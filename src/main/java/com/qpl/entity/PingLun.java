package com.qpl.entity;

public class PingLun {

	private Integer pid;
	private Integer articleId;
	private String uname;
	private String pcontent;
	private Integer zanNum;
	private Integer status;
	private String ptime;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public Integer getZanNum() {
		return zanNum;
	}
	public void setZanNum(Integer zanNum) {
		this.zanNum = zanNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	@Override
	public String toString() {
		return "PingLun [pid=" + pid + ", articleId=" + articleId + ", uname=" + uname + ", pcontent=" + pcontent
				+ ", zanNum=" + zanNum + ", status=" + status + ", ptime=" + ptime + "]";
	}
	
	public boolean isAllNull(){
		if(pid!=null){
			return false;
		}
		if(articleId!=null){
			return false;
		
		}
		if(uname!=null&&uname!=""){
			
			return false;
		}
		if(pcontent!=null&&pcontent!=""){
			return false;
		}
		if(zanNum!=null){return false;}
		if(status!=null){return false;}
		if(ptime!=null&&ptime!=""){return false;}
		return true;
	}
}
