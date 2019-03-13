package com.yuntu.curd.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectInfo {
	private Integer piId;
	
	//������ʽ��\����ת��,������java�����������ַ�,������\ǰ�ټ�һ��\
	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)",message="��̨��������Ϊ2~32λ����,����6~64λ��Сд��ĸ,����,�»���,����")
	private String piProjectname;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date piStartdate;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date piEnddate;

	private Integer piStatus;

	private Integer acid;

	// ���ڲ�ѯ����Ŀ��ͬʱ,��ѯ��Ŀ�˶�����Ϣ
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