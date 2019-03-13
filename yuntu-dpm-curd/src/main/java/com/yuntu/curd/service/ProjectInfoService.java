package com.yuntu.curd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuntu.curd.bean.ProjectInfo;
import com.yuntu.curd.bean.ProjectInfoExample;
import com.yuntu.curd.bean.ProjectInfoExample.Criteria;
import com.yuntu.curd.dao.ProjectInfoMapper;

/**
 * 项目表业务处理层
 * @author Administrator
 *
 */
@Service
public class ProjectInfoService {
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public List<ProjectInfo> getAll() {
		return projectInfoMapper.selectByExampleWithApplicant(null);
	}
	
	/**
	 * 保存项目信息
	 * @param projectInfo
	 */
	public void saveProjectInfo(ProjectInfo projectInfo) {
		projectInfoMapper.insertSelective(projectInfo);
	}
	
	/**
	 * 检查用户是否存在
	 */
	public boolean checkProjectInfo(String projectName) {
		//创建查询条件
		ProjectInfoExample example = new ProjectInfoExample();
		//得到条件对象
		Criteria criteria = example.createCriteria();
		criteria.andPiProjectnameEqualTo(projectName);
		//根据条件查询数量
		long count = projectInfoMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 修改时,根据pi_id查询出单个项目的信息
	 * @param pi_Id
	 * @return
	 */
	public ProjectInfo getProjectInfo(Integer pi_Id) {
		return projectInfoMapper.selectByPrimaryKeyWithApplicant(pi_Id);
	}
	
	/**
	 * 修改项目信息(根据key有选择的更新)
	 * @param projectInfo
	 */
	public void updateProjectInfo(ProjectInfo projectInfo) {
		projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
	}
	
	/**
	 * 根据主键删除id
	 * @param id
	 */
	public void deleteProjectInfo(Integer id) {
		projectInfoMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据多个id删除多个id
	 * @param ids
	 */
	public void deleteProjectInfosAny(List<Integer> ids) {
		ProjectInfoExample example = new ProjectInfoExample();
		Criteria criteria = example.createCriteria();
		//相当于在数据库中执行SQL语句 :delete from projectinfo where pi_id in (集合)
		criteria.andPiIdIn(ids);
		projectInfoMapper.deleteByExample(example);
	}
}
