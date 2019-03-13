package com.yuntu.curd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuntu.curd.bean.Applicant;
import com.yuntu.curd.bean.Msg;
import com.yuntu.curd.service.ApplicantService;

/**
 * �걨�˿�����
 * @author Administrator
 *
 */
@Controller
public class ApplicantController {
	@Autowired
	private ApplicantService applicantService;
	
	/**
	 * ��ȡ�����걨�˵���Ϣ��������JSON����
	 */
	@RequestMapping("/applicants")
	@ResponseBody  //��Ӧ��������һ��JSON����
	public Msg getApplicants() {
		List<Applicant> list = applicantService.getApplicants();
		return Msg.success().add("applicants", list);
	}
}
