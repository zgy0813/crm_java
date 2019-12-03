package com.zgy.wechat.user.service;

import com.zgy.wechat.user.entity.InviteCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fish
 * @since 2019-09-06
 */
public interface IInviteCodeService extends IService<InviteCode> {

    /**
     * 验证邀请码有效性
     * @param code 邀请码值
     * @return 是否有效
     */
    Long verifyInviteCode(String code);

    /**
     * 处理邀请码信息
     * @param code 邀请码值
     * @return 邀请码ID
     */
    Long handleInviteCode(String code);
}
