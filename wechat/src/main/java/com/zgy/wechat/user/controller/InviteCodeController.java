package com.zgy.wechat.user.controller;


import com.zgy.wechat.common.base.BaseController;
import com.zgy.wechat.user.entity.InviteCode;
import com.zgy.wechat.user.service.IInviteCodeService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fish
 * @since 2019-09-06
 */
@RestController
@RequestMapping("/invite")
public class InviteCodeController extends BaseController<InviteCode, IInviteCodeService> {

}
