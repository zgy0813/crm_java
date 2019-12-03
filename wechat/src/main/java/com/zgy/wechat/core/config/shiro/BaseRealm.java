package com.zgy.wechat.core.config.shiro;

import com.zgy.wechat.sys.auth.entity.Auth;
import com.zgy.wechat.sys.role.entity.Role;
import com.zgy.wechat.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class BaseRealm extends AuthorizingRealm {
    /**
     * 用户授权F
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {

       log.info("=============查看权限============");

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> authCollection = new HashSet<>();
            Set<Role> roles = user.getRoles();
            for(Role role : roles){
                rolesCollection.add(role.getCode());
                Set<Auth> auths = role.getAuths();
                for (Auth auth : auths){
                    authCollection.add(auth.getPermission());
                }
                // 增加权限集合
                info.addStringPermissions(authCollection);
            }
            // 增加角色集合
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
