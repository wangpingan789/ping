package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SysRoleMenu implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -918644351506853963L;
    private Integer id;
    private String name;
    private String note;
    /**角色对应的菜单id*/
	private List<Integer> menuIds;
}
