package com.cy.pj.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SysLog implements Serializable {

	private static final long serialVersionUID = 8882129031401775652L;
	
	private Integer id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //执行时长
    private Long time;
    //IP地址
    private Date createdTime;
}
