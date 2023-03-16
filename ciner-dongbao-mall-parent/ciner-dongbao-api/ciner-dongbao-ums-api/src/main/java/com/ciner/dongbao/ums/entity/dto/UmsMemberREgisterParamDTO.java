package com.ciner.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
public class UmsMemberREgisterParamDTO {

    @Size(min = 1,max = 8,message = "用户名长度在1-8之间")
    //@NotEmpty(message = "用户名不为空")
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;


}
