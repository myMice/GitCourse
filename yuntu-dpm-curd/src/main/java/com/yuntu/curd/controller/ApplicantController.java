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
 * 申报人控制器
 * @author Administrator
 *
 */
@Controller
public class ApplicantController {
	@Autowired
	private ApplicantService applicantService;
	
	/**
	 * 获取所有申报人的信息，并返回JSON数据
	 */
	@RequestMapping("/applicants")
	@ResponseBody  //响应的数据是一个JSON数据
	public Msg getApplicants() {
		List<Applicant> list = applicantService.getApplicants();
		return Msg.success().add("applicants", list);
	}
}
