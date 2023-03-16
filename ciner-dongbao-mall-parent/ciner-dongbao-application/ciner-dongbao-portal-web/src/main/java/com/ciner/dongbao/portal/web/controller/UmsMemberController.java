package com.ciner.dongbao.portal.web.controller;


import com.ciner.dongbao.common.base.result.ResultWrapper;
import com.ciner.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.ciner.dongbao.ums.entity.dto.UmsMemberREgisterParamDTO;
import com.ciner.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author 王丽琦
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/ums-member")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @PostMapping("register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberREgisterParamDTO umsMemberREgisterParamDTO){

        //int i = 1/0;
        return umsMemberService.register(umsMemberREgisterParamDTO);

    }

    @PostMapping("login")
    public ResultWrapper login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO){


        return umsMemberService.login(umsMemberLoginParamDTO);
    }
}

