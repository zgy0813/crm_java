package com.zgy.wechat.user.service;

import com.zgy.wechat.user.entity.dto.Logon;
import com.zgy.wechat.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fish
 * @since 2019-08-22
 */
public interface IUserService extends IService<User> {

    /**
     * 通过账户或者手机查询用户(不含有权限数据)
     * @param account 账户名
     * @param phone 手机号
     * @return 带权限数据的用户对象
     */
    User findByAccountOrPhone(String account,String phone);

    /**
     * 通过账户或者手机查询用户
     * @param account 账户名
     * @param phone 手机号
     * @return 带权限数据的用户对象
     */
    User findByAccountOrPhone(String account,String phone,boolean role);

    /**
     * 注册用户
     * @param logon 注册信息
     * @return 注册后的用户
     */
    User saveLogonUser(Logon logon);

    /**
     * 更新用户登录时间
     * @param id 登录用户id
     */
    void upLoginTime(Long id);
}
