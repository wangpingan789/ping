package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.annotation.RequiredCache;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.common.bo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
	private SysLogDao sysLogDao;
    @RequiredCache
	@Override
	public PageObject<SysLog> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysLogDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("系统没有查到对应记录");
		int pageSize=5;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> re = sysLogDao.findpageObjects(name, startIndex, pageSize);		
		return new PageObject<>(pageCurrent, pageSize, rowCount, re);
	}
	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("请选择一个");
		int rows = sysLogDao.deleteObjects(ids);
		if(rows==0)
		throw new ServiceException("数据已不存在");
		return rows;
	}
	@Override
	public int insertObjects(SysLog entit) {
		
		return 0;
	}
}
