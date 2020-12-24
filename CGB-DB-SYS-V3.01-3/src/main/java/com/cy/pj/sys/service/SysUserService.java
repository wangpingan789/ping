package com.cy.pj.sys.service;

import java.util.Map;


import com.cy.pj.common.vo.SysUserDept;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.pojo.SysUser;

public interface SysUserService {
	 PageObject<SysUserDept> findPageObjects(
			 String username,Integer pageCurrent);
	 int ValidById(Integer id,Integer valid);
	 int saveObject(SysUser entity,Integer[]roleIds);
	 int updateObject(SysUser entity,Integer[] roleIds);
	 Map<String,Object> findObjectById(Integer userId);
}
