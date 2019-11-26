package com.zgy.qshy.sys.role.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgy.qshy.common.base.BaseController;
import com.zgy.qshy.sys.role.entity.Role;
import com.zgy.qshy.sys.role.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2019-08-26
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role, IRoleService> {

    @ApiOperation(value = "角色数据获取(分页)")
    @PostMapping("/page")
    @RequiresRoles("admin")
    public Object page(ModelMap modelMap) {
        IPage<Role> page = service.page(new Page<>(),null);
        return setSuccessModelMap(page);
    }
}
