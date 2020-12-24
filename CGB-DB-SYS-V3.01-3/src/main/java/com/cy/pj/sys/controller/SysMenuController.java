package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {
    @Autowired
	private SysMenuService sysMenuService;
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects() {  	
    	return new JsonResult(sysMenuService.findObjects());
    }
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
    	sysMenuService.deleteObject(id);
    	return new JsonResult("删除成功");
    }
    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
    	return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysMenu entity) {
    	sysMenuService.saveObject(entity);
    	return new JsonResult("添加成功");
    }
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdataObject(SysMenu entity) {
    	sysMenuService.updataObject(entity);
    	return new JsonResult("修改成功");
    }
}
