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
 * ����dao������
 * 
 * @author Administrator �Ƽ�spring��Ŀ�Ϳ���ʹ��spring�ṩ�ĵ�Ԫ����,�����Զ�ע��������Ҫ�����
 *         1.����spring��Ԫ����ģ�� 2.ʹ��ע��@
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	ApplicantMapper applicantMapper;
	@Autowired
	ProjectInfoMapper projectInfoMapper;
	// ��������
	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD() throws ParseException {
		// ԭʼ�汾�Ĳ��Է���
		// 2.����ioc����
		// ApplicationContext ioc = new ClassPathXmlApplicationContext();
		// 2.�������л�ȡmapper
		// ApplicantExample bean = ioc.getBean(ApplicantExample.class);

		System.out.println(projectInfoMapper);

		// ����date
		// Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-11-11");
		// �����걨��
		// applicantMapper.insertSelective(new Applicant(null, "С��", date, 1, 4));

		/*
		 * ����������;ʹ������������sqlSession ��applicationContext.xml�ļ���ע�������������sqlSession����
		 */
		// ��������������ӵ�mapper�ӿڶ���
		ProjectInfoMapper mapper = sqlSession.getMapper(ProjectInfoMapper.class);
		// ѭ������
		for (int i = 0; i < 50; i++) {
			// �Զ������ַ���
			String name = UUID.randomUUID().toString().substring(0, 5) + "������̥" + i;
			// �����Ϣ
			mapper.insertSelective(
					new ProjectInfo(null, name, new SimpleDateFormat("yyyy-MM-dd").parse((1990 + i) + "-11-11"),
							new SimpleDateFormat("yyyy-MM-dd").parse((1999 + i) + "-11-11"), i % 3, i % 6 + 1, null));
		}
		System.out.println("����������!");

	}

}
