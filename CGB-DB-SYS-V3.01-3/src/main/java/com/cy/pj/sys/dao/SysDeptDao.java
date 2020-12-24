package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.pojo.SysDept;

@Mapper
public interface SysDeptDao {
	@Select("select c.*,p.name parentName from sys_depts c left join sys_depts p on c.parentId=p.id")
	List<Map<String,Object>>findObjects();
    @Select("SELECT ID,NAME,PARENTID FROM SYS_DEPTS")
    List<Node> findZTreeNodes();
    
    int updateObject(SysDept entity);
    
    int insertObject(SysDept entity);
    @Select("select count(*) from sys_depts where parentId=#{id}")
	int getChildCount(Integer id);
	  
	@Delete("delete from sys_depts where id=#{id}")
	int deleteObject(Integer id);
}
