package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysUserDept;
import com.cy.pj.sys.pojo.SysUser;
@Mapper
public interface SysUserDao {
	int getRowCount(String username);
	List<SysUserDept>findPageObjects(String username,Integer startIndex,Integer pageSize);
	SysUserDept findObjectById(Integer id);
	
	int ValidById(Integer id ,Integer valid	,String modifiedUser);
	
	int insertObject(SysUser entity);
	
	int updateObject(SysUser entity);
}
