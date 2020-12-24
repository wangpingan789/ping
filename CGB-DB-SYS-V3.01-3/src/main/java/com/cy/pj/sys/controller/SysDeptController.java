package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.pojo.SysDept;
import com.cy.pj.sys.service.SysDeptService;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
	@Autowired
     private SysDeptService sysDeptService;
	@RequestMapping("doFindObjects")
	public JsonResult dofindObjects() {
		return new JsonResult(sysDeptService.findObjects());
	}
	@RequestMapping("doFindZTreeNodes")
    public JsonResult doFindZTreeNodes() {		
		return new JsonResult(sysDeptService.findZTreeNodes());
    } 
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysDept entity) {
		sysDeptService.updateObject(entity);
		
		return new JsonResult("修改成功");
	}
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysDept entity) {
		sysDeptService.insertObject(entity);
		return new JsonResult("增加成功");
	}
	@RequestMapping("doDeleteObject")
	 public JsonResult doDeleteObject(Integer id) {
		 sysDeptService.deleteObject(id);
	
		 return new JsonResult("删除成功");
	 }
}
