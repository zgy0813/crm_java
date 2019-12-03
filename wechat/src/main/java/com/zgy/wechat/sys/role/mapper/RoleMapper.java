package com.zgy.wechat.sys.role.mapper;

import com.zgy.wechat.sys.role.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fish
 * @since 2019-08-26
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 插入用户基础角色
     */
    void insertBaseRole(@Param("cm") Map<String,Object> param);
}
