package com.ciner.dongbao.ums.service;

import com.ciner.dongbao.common.base.result.ResultWrapper;
import com.ciner.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.ciner.dongbao.ums.entity.dto.UmsMemberREgisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 王丽琦
 * @since 2023-03-14
 */
public interface UmsMemberService {
    ResultWrapper register(UmsMemberREgisterParamDTO umsMemberREgisterParamDTO);
    ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);
}
