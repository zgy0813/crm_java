package com.zgy.qshy.user.mapper;

import com.zgy.qshy.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fish
 * @since 2019-08-22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    // 查询用户信息、角色、权限
    User findById(Long id);
}
