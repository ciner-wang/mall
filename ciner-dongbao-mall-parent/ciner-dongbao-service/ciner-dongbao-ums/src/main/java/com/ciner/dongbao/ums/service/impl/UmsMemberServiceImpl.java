package com.ciner.dongbao.ums.service.impl;

import com.ciner.dongbao.ums.entity.UmsMember;
import com.ciner.dongbao.ums.mapper.UmsMemberMapper;
import com.ciner.dongbao.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 王丽琦
 * @since 2023-03-14
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    public String register(){
        UmsMember u = new UmsMember();
        u.setNickName("c");
        umsMemberMapper.insert(u);

        return "success";
    }
}
