package com.zgy.qshy.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Page对象", description="分页信息")
public class PageVo {
    @ApiModelProperty(value = "一次查询多少条数据")
    private Integer pageSize;

    @ApiModelProperty(value = "第几次查询")
    private Integer pageIndex;
}
