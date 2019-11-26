package com.zgy.qshy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.qshy.common.exception.DataException;
import com.zgy.qshy.common.util.LoginUtils;
import com.zgy.qshy.sys.role.service.IRoleService;
import com.zgy.qshy.user.entity.User;
import com.zgy.qshy.user.entity.dto.Logon;
import com.zgy.qshy.user.mapper.UserMapper;
import com.zgy.qshy.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2019-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IRoleService roleService;

    /**
     * 通过账户或者手机查询用户
     * @param account 账户名
     * @param phone 手机号
     * @return 带权限数据的用户对象
     */
    @Override
    public User findByAccountOrPhone(String account,String phone,boolean role) {
        // 查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (null != account) {
            wrapper.eq("account",account);
        }
        if (null != phone) {
            wrapper.eq("phone",phone);
        }
        User user = super.getOne(wrapper);
        if (user != null && role) {
            // 查询用户信息、角色、权限
            user = baseMapper.findById(user.getId());
        }
        return user;
    }

    /**
     * 通过账户或者手机查询用户（不含有权限数据）
     * @param account 账户名
     * @param phone 手机号
     * @return 带权限数据的用户对象
     */
    @Override
    public User findByAccountOrPhone(String account,String phone) {
        return findByAccountOrPhone(account,phone,false);
    }

    /**
     * 注册用户
     * @param logon 注册信息
     * @return 注册后的用户
     */
    @Override
    public User saveLogonUser(Logon logon) {
        // 0. 验证手机号
        if (StringUtils.isEmpty(logon.getPhone())) {
            throw new DataException("请填写手机号!");
        }
        // 1. 初始化用户信息
        User user = new User(logon.getPhone());
        // 2. 给密码加密
        user.setPassword(LoginUtils.encryptPwd(user.getAccount(),logon.getPassword()));
        // 3. 保存用户信息
        super.save(user);
        // 4. 给用户设定基础角色
        roleService.insertBaseRole(user.getId());
        return user;
    }

    /**
     * 更新用户登录时间
     * @param id 登录用户id
     */
    @Override
    public void upLoginTime(Long id) {
        User u = new User();
        u.setId(id);
        u.setLoginTime(new Date());
        super.updateById(u);
    }


}
