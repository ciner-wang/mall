package com.ciner.dongbao.ums.service.impl;

import com.ciner.dongbao.ums.entity.UmsMember;
import com.ciner.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.ciner.dongbao.ums.entity.dto.UmsMemberREgisterParamDTO;
import com.ciner.dongbao.ums.mapper.UmsMemberMapper;
import com.ciner.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UmsMemberREgisterParamDTO umsMemberREgisterParamDTO){
        UmsMember u = new UmsMember();
        BeanUtils.copyProperties(umsMemberREgisterParamDTO, u);

        String encode = passwordEncoder.encode(u.getPassword());
        u.setPassword(encode);
        umsMemberMapper.insert(u);

        return "success";
    }

    @Override
    public String login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {


        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
        if (null != umsMember){
            String passwordDb = umsMember.getPassword();
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(), passwordDb)){
                return "密码不正确";
            }
        }else {
            return "用户不存在";
        }

        return "token";
    }
}
