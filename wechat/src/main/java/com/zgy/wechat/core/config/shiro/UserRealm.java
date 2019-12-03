package com.zgy.wechat.core.config.shiro;

import com.zgy.wechat.user.entity.User;
import com.zgy.wechat.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRealm extends BaseRealm{
    @Autowired
    private IUserService userService;

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=============执行认证============");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User bean = userService.findByAccountOrPhone(token.getUsername(),null,true);
        if(bean == null){
            throw new UnknownAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(bean.getAccount());
        return new SimpleAuthenticationInfo(bean, bean.getPassword(),
                credentialsSalt, getName());
    }

    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof UsernamePasswordToken;
    }

    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "15652763305";
        // 用户密码
        String password = "123456";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
        System.out.println(obj.toString());
    }
}
