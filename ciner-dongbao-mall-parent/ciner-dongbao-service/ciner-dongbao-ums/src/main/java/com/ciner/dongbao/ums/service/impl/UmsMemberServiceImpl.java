package com.ciner.dongbao.ums.service.impl;

import com.ciner.dongbao.common.base.enums.StateCodeEnum;
import com.ciner.dongbao.common.base.result.ResultWrapper;
import com.ciner.dongbao.common.util.JwtUtil;
import com.ciner.dongbao.ums.entity.UmsMember;
import com.ciner.dongbao.ums.entity.UserMeberLoginReponse;
import com.ciner.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.ciner.dongbao.ums.entity.dto.UmsMemberREgisterParamDTO;
import com.ciner.dongbao.ums.mapper.UmsMemberMapper;
import com.ciner.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
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
    public ResultWrapper register(UmsMemberREgisterParamDTO umsMemberREgisterParamDTO){
        UmsMember u = new UmsMember();
        BeanUtils.copyProperties(umsMemberREgisterParamDTO, u);

        String encode = passwordEncoder.encode(umsMemberREgisterParamDTO.getPassword());
        u.setPassword(encode);
        umsMemberMapper.insert(u);

        return ResultWrapper.getSuccessBuilder().build();
    }



    @Override
    public ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {


        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberLoginParamDTO.getUsername());
        if (null != umsMember){
            String passwordDb = umsMember.getPassword();
            //解密校验密码
            if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(), passwordDb)){
                return ResultWrapper.getFailBuilder().code(StateCodeEnum.PASSWORD_ERROR.getCode()).msg(StateCodeEnum.PASSWORD_ERROR.getMsg()).build();
            }
        }else {
            return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_EMPTY.getCode()).msg(StateCodeEnum.USER_EMPTY.getMsg()).build();
        }

        String token = JwtUtil.createToken(umsMember.getId()+"");
        UserMeberLoginReponse userMeberLoginReponse = new UserMeberLoginReponse();
        userMeberLoginReponse.setToken(token);
        umsMember.setPassword("");
        userMeberLoginReponse.setUmsMember(umsMember);
        return ResultWrapper.getSuccessBuilder().data(userMeberLoginReponse).build();

    }

    @Override
    public ResultWrapper edit(UmsMember umsMember) {
        int i = umsMemberMapper.updateById(umsMember);


        return ResultWrapper.getSuccessBuilder().data(umsMember).build();
    }

}
