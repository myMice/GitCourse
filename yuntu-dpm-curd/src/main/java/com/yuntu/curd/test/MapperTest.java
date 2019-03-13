package com.yuntu.curd.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuntu.curd.bean.Applicant;
import com.yuntu.curd.bean.ProjectInfo;
import com.yuntu.curd.dao.ApplicantMapper;
import com.yuntu.curd.dao.ProjectInfoMapper;

/**
 * 测试dao的任务
 * 
 * @author Administrator 推荐spring项目就可以使用spring提供的单元测试,可以自动注入我们需要的组件
 *         1.导入spring单元测试模块 2.使用注解@
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	ApplicantMapper applicantMapper;
	@Autowired
	ProjectInfoMapper projectInfoMapper;
	// 批量操作
	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD() throws ParseException {
		// 原始版本的测试方法
		// 2.创建ioc容器
		// ApplicationContext ioc = new ClassPathXmlApplicationContext();
		// 2.从容器中获取mapper
		// ApplicantExample bean = ioc.getBean(ApplicantExample.class);

		System.out.println(projectInfoMapper);

		// 创建date
		// Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-11-11");
		// 插入申报人
		// applicantMapper.insertSelective(new Applicant(null, "小黑", date, 1, 4));

		/*
		 * 批量插入多个;使用批量操作的sqlSession 在applicationContext.xml文件中注入的批量操作的sqlSession对象
		 */
		// 创建用于批量添加的mapper接口对象
		ProjectInfoMapper mapper = sqlSession.getMapper(ProjectInfoMapper.class);
		// 循环操作
		for (int i = 0; i < 50; i++) {
			// 自动生成字符串
			String name = UUID.randomUUID().toString().substring(0, 5) + "火车修轮胎" + i;
			// 添加信息
			mapper.insertSelective(
					new ProjectInfo(null, name, new SimpleDateFormat("yyyy-MM-dd").parse((1990 + i) + "-11-11"),
							new SimpleDateFormat("yyyy-MM-dd").parse((1999 + i) + "-11-11"), i % 3, i % 6 + 1, null));
		}
		System.out.println("批量添加完成!");

	}

}
