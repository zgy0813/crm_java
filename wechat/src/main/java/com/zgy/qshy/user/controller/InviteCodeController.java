package com.zgy.qshy.user.controller;


import com.zgy.qshy.user.entity.InviteCode;
import com.zgy.qshy.user.service.IInviteCodeService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.zgy.qshy.common.base.BaseController;

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
