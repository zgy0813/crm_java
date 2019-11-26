package com.zgy.qshy.sys.auth.service.impl;

import com.zgy.qshy.sys.auth.entity.Auth;
import com.zgy.qshy.sys.auth.mapper.AuthMapper;
import com.zgy.qshy.sys.auth.service.IAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2019-08-30
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {

}
