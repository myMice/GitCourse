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
 * 项目控制器
 * @author Administrator
 *
 */
@Controller
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService projectInfoService;
	
	/**
	 * 将单个删除变成既能单个删除也能多个删除
	 * 传递的参数ids
	 * 如果是单个值 ids=1
	 * 如果是多个值ids = 1-2-3
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/projectinfo/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteProjectInfoById(@PathVariable("ids")String ids) {
		//判断是否包含横杠
		if(ids.contains("-")) {//多个
			List<Integer> int_ids = new ArrayList<Integer>();
			String [] str_ids = ids.split("-");
			//循环将字符串中的数转化为integer,并放到集合str_ids中
			for (String str_id : str_ids) {
				int_ids.add(Integer.valueOf(str_id));
			}
			projectInfoService.deleteProjectInfosAny(int_ids);
		}else {//单个
			Integer id = Integer.valueOf(ids);
			projectInfoService.deleteProjectInfo(id);
		}
		return Msg.success();
	}
	
	
	
	/**
	 * 更改数据
	 * @param projectInfo
	 * @return
	 */
	//{piId}必须是projectInfo对象的属性
	@RequestMapping(value="/projectinfo/{piId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveProjectInfo(ProjectInfo projectInfo) {
		/*
		 * PUT 方式说明
		 * Tomcat在进行应用处理时,只判断是否包含post请求,如果不是post,直接结束
		 * 所以put请求时无法拿到想要的数据,默认获得的全部是NULL
		 * 
		 * 解决方案:
		 * 必须在web.xml文件中添加HttpPutFormContentFilter过滤器
		 * 将请求中的数据解析包装成集合,同request被重新包装
		 * 这样的话,request.getParameter("")被重写,这样就能通过put请求拿到数据
		 */
		projectInfoService.updateProjectInfo(projectInfo);
		return Msg.success();
	}
	
	/**
	 * 根据pi_id查询项目的详细信息
	 * @param pi_Id
	 * @return
	 */
	@RequestMapping(value="/projectinfo/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getProjectInfo(@PathVariable("id")Integer pi_Id) {
		//@PathVariable：代表直接从路径中获取id
		ProjectInfo projectInfo = projectInfoService.getProjectInfo(pi_Id);
		return Msg.success().add("projectInfo", projectInfo);
	}
	
	/**
	 * 验证项目名是否存在
	 * @param projectName
	 * @return
	 */
	@RequestMapping("/checkProjectInfo")
	@ResponseBody
	public Msg checkProjectInfo(@RequestParam("projectName")String projectName) {
		//判断项目名是否合法
		String regProjectName="(^[a-zA-Z0-9_-]{6,64}$)|(^[\\u2E80-\\u9FFF]{2,32}$)";
		if(!projectName.matches(regProjectName)) {
			return Msg.fail().add("validate_msg", "论文名为2~32位中文,或者6~64位大小写字母,数字,下划线,横线");
		}
		//执行查询
		boolean flag = projectInfoService.checkProjectInfo(projectName);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail().add("validate_msg", "x 不可用");
		}
	}
	
	/**
	 * 保存,返回JSON数据
	 * 请求方式(规定):
	 * uri: 
	 * /projectinfo/{id} GET方式 查询
	 * /projectinfo POST方式 添加
	 * /projectinfo/{id} PUT方式 修改
	 * /projectinfo/{id} DELETE方式 删除
	 * @param projectInfo
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/projectInfo",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveProjetInfo(@Valid ProjectInfo projectInfo,BindingResult result) {
		//传递参数前添加@Valid 进行JSR303数据校验;BindingResult：绑定的验证结果
		//判断验证结果是否有错误信息
		if(result.hasErrors()){//如果有错误信息
			//创建用于存放错误的map集合
			Map<String, Object> map = new HashMap<String, Object>();
			//返回的所有的字段错误
			List<FieldError> errors = result.getFieldErrors();
			//循环将得到的所有错误,都放在map集合中
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名称："+fieldError.getField());
				System.out.println("错误的提示信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			//返回到页面处理上
			return Msg.fail().add("errorFields", map);
		}else {//没有错误信息
			projectInfoService.saveProjectInfo(projectInfo);
			return Msg.success();
		}
	}
	
	
	/**
	 * ajax请求分页查询页面数据,返回json格式
	 * @param pn
	 * @return
	 */
	@RequestMapping("/projectInfos")
	@ResponseBody
	public Msg getProjectInfosWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn, 5);
		List<ProjectInfo> infos = projectInfoService.getAll();
		PageInfo page = new PageInfo(infos,5);
		//将pageinfo对象中的所有信息进行返回
		return Msg.success().add("pageinfo", page);
	}

}
