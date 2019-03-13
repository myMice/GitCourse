package com.yuntu.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.curd.bean.ProjectInfo;
import com.yuntu.curd.bean.ProjectInfoExample;
import com.yuntu.curd.bean.ProjectInfoExample.Criteria;
import com.yuntu.curd.dao.ProjectInfoMapper;

/**
 * ��Ŀ��ҵ�����
 * @author Administrator
 *
 */
@Service
public class ProjectInfoService {
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	
	/**
	 * ��ѯ������Ŀ
	 * @return
	 */
	public List<ProjectInfo> getAll() {
		return projectInfoMapper.selectByExampleWithApplicant(null);
	}
	
	/**
	 * ������Ŀ��Ϣ
	 * @param projectInfo
	 */
	public void saveProjectInfo(ProjectInfo projectInfo) {
		projectInfoMapper.insertSelective(projectInfo);
	}
	
	/**
	 * ����û��Ƿ����
	 */
	public boolean checkProjectInfo(String projectName) {
		//������ѯ����
		ProjectInfoExample example = new ProjectInfoExample();
		//�õ���������
		Criteria criteria = example.createCriteria();
		criteria.andPiProjectnameEqualTo(projectName);
		//����������ѯ����
		long count = projectInfoMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * �޸�ʱ,����pi_id��ѯ��������Ŀ����Ϣ
	 * @param pi_Id
	 * @return
	 */
	public ProjectInfo getProjectInfo(Integer pi_Id) {
		return projectInfoMapper.selectByPrimaryKeyWithApplicant(pi_Id);
	}
	
	/**
	 * �޸���Ŀ��Ϣ(����key��ѡ��ĸ���)
	 * @param projectInfo
	 */
	public void updateProjectInfo(ProjectInfo projectInfo) {
		projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
	}
	
	/**
	 * ��������ɾ��id
	 * @param id
	 */
	public void deleteProjectInfo(Integer id) {
		projectInfoMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ���ݶ��idɾ�����id
	 * @param ids
	 */
	public void deleteProjectInfosAny(List<Integer> ids) {
		ProjectInfoExample example = new ProjectInfoExample();
		Criteria criteria = example.createCriteria();
		//�൱�������ݿ���ִ��SQL��� :delete from projectinfo where pi_id in (����)
		criteria.andPiIdIn(ids);
		projectInfoMapper.deleteByExample(example);
	}
}
