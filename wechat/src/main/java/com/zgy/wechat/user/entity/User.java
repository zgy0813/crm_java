package com.zgy.wechat.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zgy.wechat.common.base.BaseModel;
import com.zgy.wechat.sys.role.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fish
 * @since 2019-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
@TableName("tbl_user")
public class User extends BaseModel {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邀请码")
    private Long inviteId;

    @ApiModelProperty(value = "每天观影次数")
    private Integer watchCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色权限集合")
    private Set<Role> roles = new HashSet<>();

    public User(){

    }
    /**
     * 注册时手机号也是账户名和用户名称
     * @param phone 注册是手机号
     */
    public User(String phone) {
        this.name = phone;
        this.phone = phone;
        this.account = phone;
        this.createTime = new Date();
    }
}
