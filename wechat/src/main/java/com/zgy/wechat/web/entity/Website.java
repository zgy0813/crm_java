package com.zgy.wechat.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zgy.wechat.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="website对象", description="")
@TableName("tbl_website")
public class Website extends BaseModel {

    @ApiModelProperty(value = "网址密文")
    private String code;

    @ApiModelProperty(value = "真实网址")
    private String url;

    @ApiModelProperty(value = "二维码地址")
    private String picPath;

}
