package com.yuntu.curd.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.yuntu.curd.bean.ProjectInfo;

/**
 * 使用spring测试模块发送页面虚拟请求，测试页面curd操作
 * 
 * @author Administrator
 *
 */
//使用spring提供的单元测试组件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
@WebAppConfiguration
public class MVCTest {
	// 传入springmvc的容器
	@Autowired
	WebApplicationContext context;
	// 虚拟mvc请求,获取处理结果
	MockMvc mockMvc;

	// 每次请求前都要初始化构建mockMvc
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		/*
		 * MockMvcRequestBuilders.get("/projectInfos"):使用springmvc发送请求信息,而且是get请求
		 * param("pn", "7")：携带一个参数 params(arg0)：携带多个参数
		 */
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/projectInfos").param("pn", "7")).andReturn();
		// 请求成功之后,请求域就会有pageinfo,可以取出
		MockHttpServletRequest request = result.getRequest();
		// 从请求中拿到PageInfo信息
		PageInfo pe = (PageInfo) request.getAttribute("pageinfo");
		//输出验证
		System.out.println("当前页" + pe.getPageNum());
		System.out.println("页容量" + pe.getPageSize());
		System.out.println("起始数" + pe.getStartRow());
		System.out.println("结束数" + pe.getEndRow());
		System.out.println("总条数" + pe.getTotal());
		System.out.println("总页数" + pe.getPages());
		System.out.println("当前应该连续展示的页码");
		int[] nums = pe.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(" " + i);
		}
		System.out.println();
		// 获取当前页的项目信息
		List<ProjectInfo> list = pe.getList();
		for (ProjectInfo projectInfo : list) {
			System.out.println("ID:" + projectInfo.getPiId() + "\t项目名" + projectInfo.getPiProjectname() + "\t申报人"
					+ projectInfo.getApplicant().getAcName());
		}
	}
}
