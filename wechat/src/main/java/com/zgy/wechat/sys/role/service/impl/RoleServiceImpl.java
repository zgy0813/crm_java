package com.zgy.wechat.sys.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.zgy.wechat.core.Constants;
import com.zgy.wechat.sys.role.entity.Role;
import com.zgy.wechat.sys.role.mapper.RoleMapper;
import com.zgy.wechat.sys.role.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2019-08-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 插入基础用户角色
     */
    public void insertBaseRole(Long userId) {
        Map<String,Object> param = new HashMap<>();
        param.put("id", IdWorker.getId());
        param.put("userId",userId);
        // 获取基础角色
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("code", Constants.ROLE.BASE);
        Role role = super.getOne(wrapper);
        if (null != role) {
            // 给用户赋予基础角色权限
            param.put("roleId",role.getId());
            baseMapper.insertBaseRole(param);
        }
    }
}
