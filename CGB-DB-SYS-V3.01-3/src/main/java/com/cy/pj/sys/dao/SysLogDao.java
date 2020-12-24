package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.sys.pojo.SysLog;

@Mapper
public interface SysLogDao {
   int getRowCount(String username);
   List<SysLog>findpageObjects(String username,Integer startIndex,Integer pageSize);
   int deleteObjects(Integer...ids);
   int insertObjects(SysLog entit);


}
