package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuDao {
	//菜单id删除
	int deleteObjectsById(Integer menuId);
	//角色id删除
	int deleteObjectsByRoleId(Integer roleId);
	//角色与菜单的关系
	int insertObjects(Integer roleId,Integer[] menuIds);
}
