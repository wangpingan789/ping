package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;

import com.cy.pj.common.vo.SysUserDept;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
		//1.参数有效性校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码值无效");
		//2.查询总记录数并校验
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDept> records=
				sysUserDao.findPageObjects(username, startIndex, pageSize);
		//4.封装查询结果并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public int ValidById(Integer id, Integer valid) {
		if(id==null)
			throw new ServiceException("参数不合法,id="+id);
		if(valid==null||(valid!=1&&valid!=0))
			throw new ServiceException("参数不合法,valid="+valid);
		int rows = sysUserDao.ValidById(id, valid, "admin");
		if(rows==0)
			throw new ServiceException("记录可能不存在了");
		return rows;
	}
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {

		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if(roleIds==null || roleIds.length==0)
			throw new IllegalArgumentException("至少要为用户分配角色");
		//2.保存用户自身信息
		//2.1对密码进行加密
		String source=entity.getPassword();
		String salt=UUID.randomUUID().toString();
		SimpleHash sh=new SimpleHash(//Shiro框架
				"MD5",//algorithmName 算法
				source,//原密码
				salt, //盐值
				1);//hashIterations表示加密次数
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		int rows=sysUserDao.insertObject(entity);
		//3.保存用户角色关系数据
		sysUserRoleDao.insertObjects(entity.getId(), roleIds);


		//4.返回结果
		return rows;
	}
	@Override
	public int updateObject(SysUser entity,Integer[] roleIds) {
		if(entity==null)
			throw new IllegalArgumentException("保存的对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		    throw new IllegalArgumentException("用户名不能为空");
		    if(roleIds==null||roleIds.length==0)
			   throw new IllegalArgumentException("必须为其指定角色");
		    int rows = sysUserDao.updateObject(entity);
		    sysUserRoleDao.deleteObjectsByuserId(entity.getId());
		    sysUserRoleDao.insertObjects(entity.getId(), roleIds);
		    
            
            	
		return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		//1.合法星验证
		if(userId==null||userId<=0)
		throw new IllegalArgumentException("参数不合法,userId="+userId);
		//业务查询
		SysUserDept user = sysUserDao.findObjectById(userId);
		if(user==null)
		throw new ServiceException("此用户不存在");
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
		//数据封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}



}
