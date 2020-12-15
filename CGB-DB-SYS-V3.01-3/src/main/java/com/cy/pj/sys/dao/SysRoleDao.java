package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysRoleMenu;
import com.cy.pj.sys.pojo.SysRole;

@Mapper
public interface SysRoleDao {
	SysRoleMenu findObjectById(Integer id);
    int getRowCount(String name);
    List<SysRole> findPageObjects(String name,Integer startIndex,Integer pageSize);
    //菜单id删除角色记录
    int deleteObject(Integer id);
    int insertObject(SysRole entity);
    int updateObject(SysRole entity);
    List<CheckBox> findObjects();
}
