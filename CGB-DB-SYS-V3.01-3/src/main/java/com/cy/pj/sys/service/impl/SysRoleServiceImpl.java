package com.cy.pj.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysRoleMenu;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
    private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
           throw new IllegalArgumentException("当前页码无效");
       int rowCount = sysRoleDao.getRowCount(name);
       if(rowCount==0)
    	   throw new ServiceException("没有找不到对应内容");
       int pageSize=5;
       int startIndex=(pageCurrent-1)*pageSize;
         List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
         
			return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1)
			throw new IllegalArgumentException("请先选择");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("此纪录有可能不存在");
		
		return rows;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不允许为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色分配权限");
		int rows = sysRoleDao.insertObject(entity);
		//3.保存角色菜单关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		return rows;
	}
	@Override
	public SysRoleMenu findObjectById(Integer id) {
     if(id==null||id<=0)
    	 throw new IllegalArgumentException("id的值不合法");
     SysRoleMenu rows = sysRoleDao.findObjectById(id);
     if(rows==null)
    	 throw new ServiceException("此记录已经不存在");
     	return rows;		
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		if(entity==null)
			throw new IllegalArgumentException("记录不存在");
		if(entity.getName()==null)
			throw new IllegalArgumentException("id的值不存在");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色不能为空");
		if(menuIds==null||menuIds.length==0)		
			throw new IllegalArgumentException("必须为角色指定一个权限");
		int rows = sysRoleDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("对象可能不存在");
		sysRoleMenuDao.deleteObjectsById(entity.getId());
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);		
		return rows;
	}
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}

}
