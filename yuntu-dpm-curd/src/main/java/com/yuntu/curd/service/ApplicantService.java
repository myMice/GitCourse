package com.yuntu.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.curd.bean.Applicant;
import com.yuntu.curd.dao.ApplicantMapper;

/**
 * ������ҵ���
 * @author Administrator
 *
 */
@Service
public class ApplicantService {
	@Autowired
	private ApplicantMapper applicantMapper;
	
	/**
	 * ҵ���ѯ���е��걨��
	 * @return
	 */
	public List<Applicant> getApplicants() {
		return applicantMapper.selectByExample(null);

	}
}
