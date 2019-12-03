package com.zgy.wechat.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.zgy.wechat.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fish
 * @since 2019-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_user_invite_code")
@ApiModel(value="InviteCode对象", description="邀请码")
public class InviteCode extends BaseModel {

    @ApiModelProperty(value = "邀请码")
    private String code;

    @ApiModelProperty(value = "状态:0：有效 1：无效")
    private Integer state;

    @ApiModelProperty(value = "有效期")
    private Date expiryDate;

    @ApiModelProperty(value = "有效次数")
    private Integer expiryCount;

    @ApiModelProperty(value = "使用次数")
    private Integer count;


}
