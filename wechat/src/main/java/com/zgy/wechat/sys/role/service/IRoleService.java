package com.zgy.wechat.sys.role.service;

import com.zgy.wechat.sys.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fish
 * @since 2019-08-26
 */
public interface IRoleService extends IService<Role> {

    /**
     * 插入基础用户角色
     */
    void insertBaseRole(Long userId);

}
