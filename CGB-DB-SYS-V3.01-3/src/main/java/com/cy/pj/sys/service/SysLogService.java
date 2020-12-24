package com.cy.pj.sys.service;

import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.pojo.SysLog;

public interface SysLogService {
 PageObject<SysLog> findPageObjects(String username,Integer pageCurrent); 
 int deleteObjects(Integer...ids);
 int insertObjects(SysLog entit);
}

