package com.zgy.qshy.core.config.shiro;

import com.zgy.qshy.user.entity.User;
import com.zgy.qshy.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class PhoneRealm extends BaseRealm {
    @Autowired
    private IUserService userService;

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=============执行认证============");
        PhoneToken token = (PhoneToken)authenticationToken;
        User bean = userService.findByAccountOrPhone(null,token.getPhone(),true);
        if(bean == null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(bean, bean.getPhone(),  getName());
    }

    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof PhoneToken;
    }
}
