package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysUserRoleDao {
    int deleteObjectsByRoleId(Integer roleId);
    List<Integer> findRoleIdsByUserId(Integer id);
    
    
    int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
   int deleteObjectsByuserId(Integer userId);
}
