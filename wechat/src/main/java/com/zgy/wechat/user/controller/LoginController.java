package com.zgy.wechat.user.controller;


import com.zgy.wechat.common.base.AbstractController;
import com.zgy.wechat.common.util.LoginUtils;
import com.zgy.wechat.core.config.shiro.PhoneToken;
import com.zgy.wechat.common.exception.DataException;
import com.zgy.wechat.common.support.HttpCode;
import com.zgy.wechat.core.Constants;
import com.zgy.wechat.user.entity.dto.Login;
import com.zgy.wechat.user.entity.dto.Logon;
import com.zgy.wechat.user.entity.User;
import com.zgy.wechat.user.service.IInviteCodeService;
import com.zgy.wechat.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户登录
 * @author fish
 * @version 1.0
 */
@RestController
@Api(value = "登录接口")
public class LoginController extends AbstractController {

	@Autowired
	IUserService userService;

	@Autowired
	IInviteCodeService inviteCodeService;

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public Object login(@ApiParam(required = true, value = "登录信息") @RequestBody Login login, ModelMap modelMap){
		// 1.获取Subject
		Subject subject = SecurityUtils.getSubject();
		User user ;
		// 2.获取登录类型
		if (null != login.getPhone() && null != login.getPhoneCode()) {
			// 2.1 手机验证码登录
			// 2.2 验证短信验证码正确性 todo

			// 2.3 执行登录方法
			PhoneToken token = new PhoneToken(login.getPhone());
			try{
				subject.login(token);
			} catch (UnknownAccountException | IncorrectCredentialsException e){
				return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
			}
		} else {
			// 3.1 账号密码登录
			if (null != login.getPhone() && null == login.getAccount()) {
				// 手机号+密码登录（手机号就是账号）
				login.setAccount(login.getPhone());
			}
			// 3.2 账户名或者密码为空 登录失败
			if (null == login.getAccount() || null == login.getPassword()) {
				return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
			}
			UsernamePasswordToken token = new UsernamePasswordToken(login.getAccount(), login.getPassword());
			// 3.3 执行登录方法
			try{
				subject.login(token);
			} catch (UnknownAccountException | IncorrectCredentialsException e){
				return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
			}
		}
		// 3. 更新用户登录时间
		user = (User)subject.getPrincipal();
		userService.upLoginTime(user.getId());
		user.setPassword(null);
		return setSuccessModelMap(user);
	}

	@ApiOperation(value = "用户登出")
	@PostMapping("/logout")
	public Object logout(ModelMap modelMap) {
		ResponseEntity<ModelMap> result = setSuccessModelMap(modelMap);
		SecurityUtils.getSubject().logout();
		return result;
	}

	@ApiOperation(value = "获取用户角色和权限")
	@GetMapping("/initUser")
	public Object init() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		// 密码置空
		user.setPassword(null);
		return setSuccessModelMap(user);
	}

	@ApiOperation(value = "注册前验证手机号和邀请码")
	@PostMapping("/logon/check")
	public Object check(@ApiParam(required = true, value = "注册信息") @RequestBody Logon logon) {
		if (StringUtils.isNotEmpty(logon.getPhone())) {
			// 1. 验证手机号有没有被注册过
			User user = userService.findByAccountOrPhone(null,logon.getPhone());
			if (null != user) {
				return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"手机号已注册过!");
			}
		} else {
			return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"请填写手机号!");
		}
		// 2. 验证邀请码有效性
		if (StringUtils.isNotEmpty(logon.getInviteCode())) {
			Long codeId = inviteCodeService.verifyInviteCode(logon.getInviteCode());
			if (null == codeId) {
				return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"无效的邀请码!");
			}
		}
		return setSuccessModelMap(logon);
	}

	@ApiOperation(value = "用户注册")
	@PostMapping("/logon/register")
	@Transactional
	public Object register(@RequestBody Logon logon,ModelMap modelMap) {
		// 1.验证手机验证码 todo

		// 2.保存用户信息
		User user;
		try {
			// 2. 保存用户信息
			user = userService.saveLogonUser(logon);
			// 3. 处理邀请码
			Long inviteId = inviteCodeService.handleInviteCode(logon.getInviteCode());
			// 4. 更新用户信息 每天观影次数
			if (null != inviteId) {
				user.setInviteId(inviteId);
				user.setWatchCount(Constants.USER.WATCH_COUNT.HAS_INVITE_CODE);
			} else {
				user.setWatchCount(Constants.USER.WATCH_COUNT.NO_INVITE_CODE);
			}
			userService.updateById(user);
		} catch (DataException e) {
			e.handler(modelMap);
		}
		return setSuccessModelMap(logon);
	}

	@ApiOperation(value = "注册后初始化用户后登录")
	@PostMapping("/logon/init")
	@Transactional
	public Object init(@RequestBody Logon logon,ModelMap modelMap) {
		// 1. 获取用户信息
		User user = userService.findByAccountOrPhone(null,logon.getPhone());

		// 2. 完善用户信息
		user.setSex(logon.getSex());
		user.setLoginTime(new Date());
		userService.updateById(user);
		// 3. 登录
		Login login = new Login(user.getAccount(),logon.getPassword());
		return login(login, modelMap);
	}

	@ApiOperation(value = "修改登录密码")
	@PostMapping("/logon/changePwd")
	@Transactional
	public Object changePwd(@RequestBody Logon logon,ModelMap modelMap) {
		// 1. 验证短信验证码 todo

		// 2. 获取用户信息
		User user = userService.findByAccountOrPhone(null,logon.getPhone());

		// 3. 用户不存在
		if (null == user) {
			return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"手机号未注册!");
		}
		// 2. 修改密码
		if (null != logon.getPassword()) {
			user.setPassword(LoginUtils.encryptPwd(user.getAccount(),logon.getPassword()));
			userService.updateById(user);
		}
		return setSuccessModelMap(modelMap);
	}
}
