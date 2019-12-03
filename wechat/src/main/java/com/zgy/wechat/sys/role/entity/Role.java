package com.zgy.wechat.sys.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zgy.wechat.common.base.BaseModel;
import com.zgy.wechat.sys.auth.entity.Auth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author fish
 * @since 2019-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Role对象", description="")
@TableName("sys_role")
public class Role extends BaseModel {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(exist = false)
    @ApiModelProperty(value = "权限集合")
    private Set<Auth> auths = new HashSet<>();
}
