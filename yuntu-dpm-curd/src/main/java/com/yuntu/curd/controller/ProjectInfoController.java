package com.yuntu.curd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntu.curd.bean.Msg;
import com.yuntu.curd.bean.ProjectInfo;
import com.yuntu.curd.service.ProjectInfoService;
/**
 * ��Ŀ������
 * @author Administrator
 *
 */
@Controller
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService projectInfoService;
	
	/**
	 * ������ɾ����ɼ��ܵ���ɾ��Ҳ�ܶ��ɾ��
	 * ���ݵĲ���ids
	 * ����ǵ���ֵ ids=1
	 * ����Ƕ��ֵids = 1-2-3
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/projectinfo/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteProjectInfoById(@PathVariable("ids")String ids) {
		//�ж��Ƿ�������
		if(ids.contains("-")) {//���
			List<Integer> int_ids = new ArrayList<Integer>();
			String [] str_ids = ids.split("-");
			//ѭ�����ַ����е���ת��Ϊinteger,���ŵ�����str_ids��
			for (String str_id : str_ids) {
				int_ids.add(Integer.valueOf(str_id));
			}
			projectInfoService.deleteProjectInfosAny(int_ids);
		}else {//����
			Integer id = Integer.valueOf(ids);
			projectInfoService.deleteProjectInfo(id);
		}
		return Msg.success();
	}
	
	
	
	/**
	 * ��������
	 * @param projectInfo
	 * @return
	 */
	//{piId}������projectInfo���������
	@RequestMapping(value="/projectinfo/{piId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveProjectInfo(ProjectInfo projectInfo) {
		/*
		 * PUT ��ʽ˵��
		 * Tomcat�ڽ���Ӧ�ô���ʱ,ֻ�ж��Ƿ����post����,�������post,ֱ�ӽ���
		 * ����put����ʱ�޷��õ���Ҫ������,Ĭ�ϻ�õ�ȫ����NULL
		 * 
		 * �������:
		 * ������web.xml�ļ������HttpPutFormContentFilter������
		 * �������е����ݽ�����װ�ɼ���,ͬrequest�����°�װ
		 * �����Ļ�,request.getParameter("")����д,��������ͨ��put�����õ�����
		 */
		projectInfoService.updateProjectInfo(projectInfo);
		return Msg.success();
	}
	
	/**
	 * ����pi_id��ѯ��Ŀ����ϸ��Ϣ
	 * @param pi_Id
	 * @return
	 */
	@RequestMapping(value="/projectinfo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getProjectInfo(@PathVariable("id")Integer pi_Id) {
		//@PathVariable������ֱ�Ӵ�·���л�ȡid
		ProjectInfo projectInfo = projectInfoService.getProjectInfo(pi_Id);
		return Msg.success().add("projectInfo", projectInfo);
	}
	
	/**
	 * ��֤��Ŀ���Ƿ����
	 * @param projectName
	 * @return
	 */
	@RequestMapping("/checkProjectInfo")
	@ResponseBody
	public Msg checkProjectInfo(@RequestParam("projectName")String projectName) {
		//�ж���Ŀ���Ƿ�Ϸ�
		String regProjectName="(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)";
		if(!projectName.matches(regProjectName)) {
			return Msg.fail().add("validate_msg", "������Ϊ2~32λ����,����6~64λ��Сд��ĸ,����,�»���,����");
		}
		//ִ�в�ѯ
		boolean flag = projectInfoService.checkProjectInfo(projectName);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail().add("validate_msg", "x ������");
		}
	}
	
	/**
	 * ����,����JSON����
	 * ����ʽ(�涨):
	 * uri: 
	 * /projectinfo/{id} GET��ʽ ��ѯ
	 * /projectinfo POST��ʽ ���
	 * /projectinfo/{id} PUT��ʽ �޸�
	 * /projectinfo/{id} DELETE��ʽ ɾ��
	 * @param projectInfo
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/projectInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveProjetInfo(@Valid ProjectInfo projectInfo,BindingResult result) {
		//���ݲ���ǰ���@Valid ����JSR303����У��;BindingResult���󶨵���֤���
		//�ж���֤����Ƿ��д�����Ϣ
		if(result.hasErrors()){//����д�����Ϣ
			//�������ڴ�Ŵ����map����
			Map<String, Object> map = new HashMap<String, Object>();
			//���ص����е��ֶδ���
			List<FieldError> errors = result.getFieldErrors();
			//ѭ�����õ������д���,������map������
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ����ƣ�"+fieldError.getField());
				System.out.println("�������ʾ��Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			//���ص�ҳ�洦����
			return Msg.fail().add("errorFields", map);
		}else {//û�д�����Ϣ
			projectInfoService.saveProjectInfo(projectInfo);
			return Msg.success();
		}
	}
	
	
	/**
	 * ajax�����ҳ��ѯҳ������,����json��ʽ
	 * @param pn
	 * @return
	 */
	@RequestMapping("/projectInfos")
	@ResponseBody
	public Msg getProjectInfosWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn, 5);
		List<ProjectInfo> infos = projectInfoService.getAll();
		PageInfo page = new PageInfo(infos,5);
		//��pageinfo�����е�������Ϣ���з���
		return Msg.success().add("pageinfo", page);
	}

}
