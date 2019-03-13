package com.yuntu.curd.bean;

import java.util.Date;

public class Applicant {
    private Integer acId;

    private String acName;

    private Date acBirthday;

    private Integer acSex;

    private Integer workinglife;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
    }

    public Date getAcBirthday() {
        return acBirthday;
    }

    public void setAcBirthday(Date acBirthday) {
        this.acBirthday = acBirthday;
    }

    public Integer getAcSex() {
        return acSex;
    }

    public void setAcSex(Integer acSex) {
        this.acSex = acSex;
    }

    public Integer getWorkinglife() {
        return workinglife;
    }

    public void setWorkinglife(Integer workinglife) {
        this.workinglife = workinglife;
    }

	public Applicant(Integer acId, String acName, Date acBirthday, Integer acSex, Integer workinglife) {
		super();
		this.acId = acId;
		this.acName = acName;
		this.acBirthday = acBirthday;
		this.acSex = acSex;
		this.workinglife = workinglife;
	}

	public Applicant() {
		super();
	}
    
    
}