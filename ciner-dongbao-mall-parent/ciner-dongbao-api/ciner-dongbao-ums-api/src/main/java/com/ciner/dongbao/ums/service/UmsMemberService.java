package com.ciner.dongbao.ums.service;

import com.ciner.dongbao.ums.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 王丽琦
 * @since 2023-03-14
 */
public interface UmsMemberService extends IService<UmsMember> {
    public String register();
}
