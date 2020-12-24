package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.SysRoleMenu;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.pojo.SysRole;

public interface SysRoleService {
   PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
 
   int deleteObject(Integer id);
   int saveObject(SysRole entity,Integer[]menuIds);
   SysRoleMenu findObjectById(Integer id) ;
   int updateObject(SysRole entity,Integer[]menuIds);
   List<CheckBox> findObjects();
}
