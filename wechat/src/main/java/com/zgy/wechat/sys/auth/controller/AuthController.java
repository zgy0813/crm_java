package com.zgy.wechat.sys.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.wechat.common.base.BaseController;
import com.zgy.wechat.sys.auth.entity.Auth;
import com.zgy.wechat.sys.auth.service.IAuthService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2019-08-30
 */
@RestController
@RequestMapping("/auth")
public class AuthController  extends BaseController<Auth, IAuthService> {

    @ApiOperation(value = "权限数据获取(分页)")
    @PostMapping("/page")
    @RequiresRoles("admin")
    public Object page(@RequestBody Map<String,Object> param) {
        QueryWrapper<Auth> queryWrapper = new QueryWrapper<>();
        // 用户名称检索
        if (null != param.get("permission") && !"".equals(param.get("permission"))) {
            queryWrapper.like("permission",param.get("permission"));
        }
        return super.page(param,queryWrapper);
    }

    @ApiOperation(value = "新增权限数据")
    @PostMapping
    @RequiresRoles("admin")
    public Object add(@RequestBody Auth auth) {
        // 调用新增的方法
        service.save(auth);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "修改权限数据")
    @PutMapping
    @RequiresRoles("admin")
    public Object update(@RequestBody Auth auth) {
        // 调用修改的方法
        service.updateById(auth);
        return setSuccessModelMap();
    }

    @ApiOperation(value = "删除权限数据")
    @DeleteMapping
    @RequiresRoles("admin")
    public Object delete(@RequestBody Auth auth) {
        // 调用删除的方法
        service.removeById(auth.getId());
        return setSuccessModelMap();
    }
}
