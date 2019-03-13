package com.yuntu.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.curd.bean.Applicant;
import com.yuntu.curd.dao.ApplicantMapper;

/**
 * 申请人业务层
 * @author Administrator
 *
 */
@Service
public class ApplicantService {
	@Autowired
	private ApplicantMapper applicantMapper;
	
	/**
	 * 业务查询所有的申报人
	 * @return
	 */
	public List<Applicant> getApplicants() {
		return applicantMapper.selectByExample(null);

	}
}
