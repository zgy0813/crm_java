package com.zgy.qshy.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.qshy.common.base.BaseController;
import com.zgy.qshy.user.entity.User;
import com.zgy.qshy.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2019-08-22
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,IUserService> {

    @ApiOperation(value = "用户数据获取(分页)")
    @PostMapping("/page")
    @RequiresPermissions("user:read")
    public Object page(@RequestBody Map<String,Object> param, ModelMap modelMap) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 用户名称检索
        if (null != param.get("name") && !"".equals(param.get("name"))) {
            queryWrapper.like("name",param.get("name"));
        }
        return super.page(param,queryWrapper);
    }

}
