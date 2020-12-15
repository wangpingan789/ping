package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.pojo.SysDept;

public interface SysDeptService {
	List<Map<String,Object>>findObjects();
	
	List<Node> findZTreeNodes();
	int updateObject(SysDept entity);
	int insertObject(SysDept entity);
	int deleteObject(Integer id);
}
