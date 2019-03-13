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
 * ʹ��spring����ģ�鷢��ҳ���������󣬲���ҳ��curd����
 * 
 * @author Administrator
 *
 */
//ʹ��spring�ṩ�ĵ�Ԫ�������
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
@WebAppConfiguration
public class MVCTest {
	// ����springmvc������
	@Autowired
	WebApplicationContext context;
	// ����mvc����,��ȡ������
	MockMvc mockMvc;

	// ÿ������ǰ��Ҫ��ʼ������mockMvc
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testPage() throws Exception {
		/*
		 * MockMvcRequestBuilders.get("/projectInfos"):ʹ��springmvc����������Ϣ,������get����
		 * param("pn", "7")��Я��һ������ params(arg0)��Я���������
		 */
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/projectInfos").param("pn", "7")).andReturn();
		// ����ɹ�֮��,������ͻ���pageinfo,����ȡ��
		MockHttpServletRequest request = result.getRequest();
		// ���������õ�PageInfo��Ϣ
		PageInfo pe = (PageInfo) request.getAttribute("pageinfo");
		//�����֤
		System.out.println("��ǰҳ" + pe.getPageNum());
		System.out.println("ҳ����" + pe.getPageSize());
		System.out.println("��ʼ��" + pe.getStartRow());
		System.out.println("������" + pe.getEndRow());
		System.out.println("������" + pe.getTotal());
		System.out.println("��ҳ��" + pe.getPages());
		System.out.println("��ǰӦ������չʾ��ҳ��");
		int[] nums = pe.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(" " + i);
		}
		System.out.println();
		// ��ȡ��ǰҳ����Ŀ��Ϣ
		List<ProjectInfo> list = pe.getList();
		for (ProjectInfo projectInfo : list) {
			System.out.println("ID:" + projectInfo.getPiId() + "\t��Ŀ��" + projectInfo.getPiProjectname() + "\t�걨��"
					+ projectInfo.getApplicant().getAcName());
		}
	}
}
