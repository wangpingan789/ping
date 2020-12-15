package com.cy.pj.sys.pojo;

import java.util.Date;

import lombok.Data;
@Data
public class SysRole {
    private Integer id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
	private String modifiedUser;
}
