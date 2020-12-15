package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.pojo.SysDept;
import com.cy.pj.sys.service.SysDeptService;
@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
   private SysDeptDao sysDeptDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysDeptDao.findObjects();
		if(list==null||list.size()==0)
		throw new SecurityException("没有部门信息");
		return list;
	}
	@Override
	public List<Node> findZTreeNodes() {
		List<Node> list = sysDeptDao.findZTreeNodes();
		if(list==null||list.size()==0)
			throw new SecurityException("没有部门信息");
		return list;
	}
	@Override
	public int updateObject(SysDept entity) {
		if(entity==null)
		  throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		  throw new SecurityException("部门不能为空");
		int rows;
		try {
			rows = sysDeptDao.updateObject(entity);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("更新失败");
		}	
		return rows;
	}
	@Override
	public int insertObject(SysDept entity) {
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("部门不能为空");
		int rows = sysDeptDao.insertObject(entity);
		return rows;
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<=0)
			throw new ServiceException("数据不合法,id="+id);
		int childCount = sysDeptDao.getChildCount(id);
		if(childCount>0)
			throw new ServiceException("此元素有子元素,不允许删除");
		int rows = sysDeptDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("此信息可能以及不存在");
		return rows;
	}
}
