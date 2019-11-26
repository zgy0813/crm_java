package com.zgy.qshy.common.base;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgy.qshy.common.exception.BaseException;
import com.zgy.qshy.common.exception.IllegalParameterException;
import com.zgy.qshy.common.exception.UnauthorizedException;
import com.zgy.qshy.common.support.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
@Slf4j
public abstract class AbstractController {

    @Autowired
    private HttpServletRequest request;

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap() {
        return setSuccessModelMap(new ModelMap(), null);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(Object data) {
        return setModelMap(new ModelMap(), HttpCode.OK, data);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    /** 设置失败响应代码 */
    protected ResponseEntity<ModelMap> setFailureModelMap(ModelMap modelMap) {
        return setFailureModelMap(modelMap, null);
    }

    /** 设置失败响应代码 */
    protected ResponseEntity<ModelMap> setFailureModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.BAD_REQUEST, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(HttpCode code) {
        return setModelMap(new ModelMap(), code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(Integer code, String msg) {
        return setModelMap(new ModelMap(), code, msg, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(HttpCode code, Object data) {
        return setModelMap(new ModelMap(), code, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(Integer code, String msg, Object data) {
        return setModelMap(new ModelMap(), code, msg, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        return setModelMap(modelMap, code.value(), code.msg(), data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, Integer code, String msg, Object data) {
        if (!modelMap.isEmpty()) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.putAll(modelMap);
            modelMap.clear();
            for (String key : map.keySet()) {
                if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
                    modelMap.put(key, map.get(key));
                }
            }
        }
        if (data != null) {
            if (data instanceof Page<?>) {
                Page<?> page = (Page<?>)data;
                modelMap.put("data", page.getRecords());
                modelMap.put("current", page.getCurrent());
                modelMap.put("size", page.getSize());
                modelMap.put("total", page.getTotal());
            } else if (data instanceof Page<?>) {
                Page<?> page = (Page<?>)data;
                modelMap.put("data", page.getRecords());
                modelMap.put("current", page.getCurrent());
                modelMap.put("size", page.getSize());
                modelMap.put("pages", page.getPages());
                modelMap.put("total", page.getTotal());
            } else if (data instanceof List<?>) {
                modelMap.put("data", data);
                modelMap.put("total", ((List<?>)data).size());
            } else {
                modelMap.put("data", data);
            }
        }
        modelMap.put("code", code);
        modelMap.put("msg", msg);
        modelMap.put("timestamp", System.currentTimeMillis());
        String resp = JSON.toJSONString(modelMap);
        log.info("response===>{}", resp);

        return ResponseEntity.ok(modelMap);
    }

    private static String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();

    }


    /** 异常处理 */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        log.error(BaseConstant.EXCEPTION_HEAD, ex);
        ModelMap modelMap = new ModelMap();
        if (ex instanceof BaseException) {
            ((BaseException) ex).handler(modelMap);
        } else if (ex instanceof IllegalArgumentException) {
            new IllegalParameterException(ex.getMessage()).handler(modelMap);
        } else if (ex instanceof UnauthorizedException) {
            modelMap.put("code", HttpCode.FORBIDDEN.value());
            modelMap.put("msg", StringUtils.defaultIfEmpty(ex.getMessage(), HttpCode.FORBIDDEN.msg()));
        } else {
            modelMap.put("code", HttpCode.INTERNAL_SERVER_ERROR.value());
            String msg = StringUtils.defaultIfEmpty(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
            modelMap.put("msg", msg.length() > 100 ? "系统异常,请稍候再试." : msg);
        }
        response.setContentType("application/json;charset=UTF-8");
        modelMap.put("timestamp", System.currentTimeMillis());
        // log.info(JSON.toJSON(modelMap));
        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }

}
