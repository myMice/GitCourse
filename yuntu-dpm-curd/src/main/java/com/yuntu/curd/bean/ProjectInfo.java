package com.yuntu.curd.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectInfo {
	private Integer piId;
	
	//正则表达式中\用来转义,但是在java中属于特殊字符,所以在\前再加一个\
	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)",message="后台：论文名为2~32位中文,或者6~64位大小写字母,数字,下划线,横线")
	private String piProjectname;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date piStartdate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date piEnddate;

	private Integer piStatus;

	private Integer acid;

	// 用于查询出项目的同时,查询项目人对象信息
	private Applicant applicant;

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public ProjectInfo() {
	}

	public ProjectInfo(Integer piId, String piProjectname, Date piStartdate, Date piEnddate, Integer piStatus,
			Integer acid, Applicant applicant) {
		super();
		this.piId = piId;
		this.piProjectname = piProjectname;
		this.piStartdate = piStartdate;
		this.piEnddate = piEnddate;
		this.piStatus = piStatus;
		this.acid = acid;
		this.applicant = applicant;
	}

	public Integer getPiId() {
		return piId;
	}

	public void setPiId(Integer piId) {
		this.piId = piId;
	}

	public String getPiProjectname() {
		return piProjectname;
	}

	public void setPiProjectname(String piProjectname) {
		this.piProjectname = piProjectname == null ? null : piProjectname.trim();
	}

	public Date getPiStartdate() {
		return piStartdate;
	}

	public void setPiStartdate(Date piStartdate) {
		this.piStartdate = piStartdate;
	}

	public Date getPiEnddate() {
		return piEnddate;
	}

	public void setPiEnddate(Date piEnddate) {
		this.piEnddate = piEnddate;
	}

	public Integer getPiStatus() {
		return piStatus;
	}

	public void setPiStatus(Integer piStatus) {
		this.piStatus = piStatus;
	}

	public Integer getAcid() {
		return acid;
	}

	public void setAcid(Integer acid) {
		this.acid = acid;
	}
	
}