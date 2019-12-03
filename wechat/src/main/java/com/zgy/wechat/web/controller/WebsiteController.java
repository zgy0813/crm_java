package com.zgy.wechat.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.zxing.WriterException;
import com.zgy.wechat.common.base.BaseController;
import com.zgy.wechat.common.util.QRCodeUtil;
import com.zgy.wechat.common.util.StringUtil;
import com.zgy.wechat.common.support.HttpCode;
;
import com.zgy.wechat.web.entity.Website;
import com.zgy.wechat.web.service.IWebsiteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/web")
public class WebsiteController extends BaseController<Website, IWebsiteService> {

    @Autowired
    QRCodeUtil qrCodeUtil;

    @ApiOperation(value = "根据参数获取真实url跳转真实的网站地址")
    @GetMapping("/{url}")
    public Object exchange(@PathVariable("url") String url ,ModelMap modelMap) {
        if (StringUtils.isEmpty(url)) {
            return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"请输入网址!");
        }
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",url.trim());
        // 查询真实网址
        Website web = service.getOne(queryWrapper);
        if (null == web) {
            return setModelMap(HttpCode.NOT_ACCEPTABLE.value(),"没有找到对应的网站!");
        }
        // 重定向真实网址
        // String newUrl = "redirect:" + web.getUrl();
        return setSuccessModelMap(web.getUrl());
    }

    @ApiOperation(value = "转换url并生成二维码数据")
    @PostMapping("/create")
    public Object page(@ApiParam(required = true, value = "网站信息") @RequestBody Website web , ModelMap modelMap) {
        // 判断有没有重复
        QueryWrapper<Website> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",web.getUrl().trim());
        Website website = service.getOne(queryWrapper);
        if (null == website) {
            // 生成8位数网址密文
            web.setCode(StringUtil.genRandomNum(8));
            // 生成二维码图片，保存二维码地址
            try {
                web.setPicPath(qrCodeUtil.generateQRCodeImage(web.getCode()));
            } catch (IOException | WriterException e) {
                return setModelMap(HttpCode.INTERNAL_SERVER_ERROR.value(),"生成二维码失败!");
            }
            // 保存网站信息
            service.save(web);
        } else {
            // 已存在 直接赋值 不新建
            web = website;
        }
        // 切换code为完整地址
        web.setCode(getWholeUrl(web.getCode()));
        return setSuccessModelMap(web);
       }

//    @ApiOperation(value = "根据参数获取真实url跳转真实的网站地址")
//    @GetMapping("/g/{url}")
//    public void exchange2(@PathVariable("url") String url ,HttpServletRequest request, HttpServletResponse rep) throws IOException {
//        String s1 = rep.getHeader("Content-Type");
//        String s2 = rep.getHeader("Content-Disposition");
//        rep.sendRedirect("http://www.runoob.com");
//    }

    /**
     * 生成完整的code网址 ip + api + code
     */
    private String getWholeUrl(String code){
        return qrCodeUtil.getIp() + code;
    }
}
