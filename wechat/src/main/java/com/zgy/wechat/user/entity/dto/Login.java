package com.zgy.wechat.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Login对象", description="用户登录信息")
public class Login {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "手机验证码")
    private String phoneCode;

    public Login(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Login() {
    }
}
