/**
 * 
 */
package com.zgy.wechat.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器基类
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
public abstract class BaseController<T extends BaseModel, S extends IService<T>> extends AbstractController {

    @Autowired
    protected S service;

    /** 分页查询 */
    public Object page(Map<String, Object> param, QueryWrapper<T> queryWrapper) {
        // 处理分页
        int size = param.get(BaseConstant.KEY_PAGE_SIZE) == null ? BaseConstant.DEFAULT_QUERY_COUNT : (Integer)param.get(BaseConstant.KEY_PAGE_SIZE);
        int current = param.get(BaseConstant.KEY_PAGE_NUM ) == null ? 1 : (Integer)param.get(BaseConstant.KEY_PAGE_NUM );
        // 处理排序
        handleQueryOrderBy(param,queryWrapper);
        IPage<T> page = service.page(new Page<>(current,size),queryWrapper);
        return setSuccessModelMap(page);
    }

    /** 分页查询 */
    protected void handleQueryPage(PageVo pageVo) {
        // 处理分页
        int size = pageVo.getPageSize() == null ? BaseConstant.DEFAULT_QUERY_COUNT : pageVo.getPageSize();
        int index = pageVo.getPageIndex() == null ? 1 : pageVo.getPageIndex();
        pageVo.setPageSize(size);
        pageVo.setPageIndex(index);
    }

    /**
     * 处理分页排序
     */
    private void handleQueryOrderBy(Map<String, Object> param, QueryWrapper<T> queryWrapper) {
        if (null != param.get("orderBy") && !"".equals(param.get("orderBy").toString())) {
            if (null != param.get("isAsc") && !"".equals(param.get("isAsc").toString())) {
                queryWrapper.orderByAsc(param.get("orderBy").toString());
            } else {
                queryWrapper.orderByDesc(param.get("orderBy").toString());
            }
        } else {
            queryWrapper.orderByDesc("id");
        }
    }

    /**
     * HTTP参数
     */
    public String getParam(HttpServletRequest request, String key) {
        if (request.getAttribute(key) != null) {
            return request.getAttribute(key).toString().toLowerCase();
        }
        if (request.getParameter(key) != null) {
            return request.getParameter(key).toLowerCase();
        }
        return "";
    }

}
