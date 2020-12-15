package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
@RequestMapping("/log/")
@Controller
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
	 PageObject<SysLog> pageObject=
		sysLogService.findPageObjects(username,pageCurrent);
	return new JsonResult(pageObject);	
	}	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {	
		sysLogService.deleteObjects(ids);
		return new JsonResult("删除成功");
	}
}
