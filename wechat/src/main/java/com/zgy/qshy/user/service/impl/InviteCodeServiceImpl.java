package com.zgy.qshy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgy.qshy.core.Constants;
import com.zgy.qshy.user.entity.InviteCode;
import com.zgy.qshy.user.mapper.InviteCodeMapper;
import com.zgy.qshy.user.service.IInviteCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2019-09-06
 */
@Service
public class InviteCodeServiceImpl extends ServiceImpl<InviteCodeMapper, InviteCode> implements IInviteCodeService {

    /**
     * 验证邀请码有效性
     * @param code 邀请码值
     * @return 是否有效
     */
    @Override
    public Long verifyInviteCode(String code) {
        // 1.验证邀请码是否为空
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        // 2.验证邀请码的有效性
        QueryWrapper<InviteCode> codeWrapper = new QueryWrapper<>();
        codeWrapper.eq("code",code);
        codeWrapper.eq("state", Constants.YesOrNo.Yes);
        codeWrapper.ge("expiry_date",new Date());
        InviteCode inviteCode = super.getOne(codeWrapper);
        return null == inviteCode ? null : inviteCode.getId();
    }

    /**
     * 处理邀请码信息
     * @param code 邀请码值
     * @return 邀请码ID
     */
    @Override
    public Long handleInviteCode(String code) {
        // 1. 获取邀请码的id
        Long codeId = verifyInviteCode(code);

        // 2. 邀请码处理
        if (null == codeId) {
            return null;
        }
        InviteCode inviteCode = getById(codeId);

        // 2.1 使用期数+1
        int count = inviteCode.getCount();
        inviteCode.setCount(count + 1);

        // 2.2 判断是用次数是否到最大值 是的话修改邀请码状态为无效
        if (inviteCode.getCount() >= inviteCode.getExpiryCount()) {
            inviteCode.setState(Constants.YesOrNo.NO);
        }

        // 2.3 保存邀请码信息
        super.updateById(inviteCode);
        return inviteCode.getId();
    }



}
