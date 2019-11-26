/**
 * 
 */
package com.zgy.qshy.common.base;

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
//
//    /** 分页查询 */
//    public Object query(ModelMap modelMap, Map<String, Object> param) {
//        Object page = service.listByMap(param);
//        return setSuccessModelMap(modelMap, page);
//    }
//
//    /** 查询 */
//    public Object queryList(Map<String, Object> param) {
//        return query(new ModelMap(), param);
//    }
//
//    /** 查询 */
//    public Object queryList(ModelMap modelMap, Map<String, Object> param) {
//        List<?> list = service.queryList(param);
//        return setSuccessModelMap(modelMap, list);
//    }
//
//    public Object queryMapList(ModelMap modelMap, Map<String, Object> param) {
//        return setSuccessModelMap(modelMap, service.queryMapList(param));
//    }
//
//    public Object queryTree(ModelMap modelMap, Map<String, Object> param) {
//        return setSuccessModelMap(modelMap, service.queryTree(param));
//    }
//
//    public Object get(BaseModel param) {
//        return get(new ModelMap(), param);
//    }
//
//    public Object get(ModelMap modelMap, BaseModel param) {
//        Object result = service.queryById(param.getId());
//        return setSuccessModelMap(modelMap, result);
//    }
//
//    public Object update(T param) {
//        return update(new ModelMap(), param);
//    }
//
//    public Object update(ModelMap modelMap, T param) {
//        Long userId = getCurrentUser();
//        if (param.getId() == null) {
//            param.setCreateBy(userId);
//            param.setCreateTime(new Date());
//        }
////        param.setUpdateBy(userId);
////        param.setUpdateTime(new Date());
//        service.update(param);
//        return setSuccessModelMap(modelMap);
//    }
//
////    /** 物理删除 */
////    public Object delete(T param) {
////        return delete(new ModelMap(), param);
////    }
////
////    /** 物理删除 */
//    public Object delete(ModelMap modelMap, T param) {
//        Assert.notNull(param.getId(), "ID不能为空");
//        service.delete(param.getId());
//        return setSuccessModelMap(modelMap);
//    }
//
//    public Object delete(ModelMap modelMap, Map<String, Object> param) {
//        service.deleteByMap(param);
//        return setSuccessModelMap(modelMap);
//    }
//
//    /** 逻辑删除 */
//    public Object delete(HttpServletRequest request, T param) {
//        return delete(request, new ModelMap(), param);
//    }
//
//    /** 逻辑删除 */
//    public Object delete(HttpServletRequest request, ModelMap modelMap, T param) {
//        if (param.getId() != null) {
//            Assert.notNull(param.getId(), "ID不能为空");
//            service.delete(param.getId(), getCurrentUser(request));
//        }
//        if (param.getIds() != null) {
//            Assert.notNull(param.getIds(), "ID不能为空");
//            service.delete(param.getIds(), getCurrentUser(request));
//        }
//        return setSuccessModelMap(modelMap);
//    }
//
//    public void downFile(HttpServletResponse response, String OssKey) {
//        //
//    }
//
//    public void downFile(HttpServletResponse response, InputStream ins, String fileName) throws IOException {
//        BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
//        OutputStream outs = response.getOutputStream();// 获取文件输出IO流
//        BufferedOutputStream bouts = new BufferedOutputStream(outs);
//        response.setContentType("application/x-download");// 设置response内容的类型
//        response.setHeader(
//                "Content-disposition",
//                "attachment;filename="
//                        + URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息
//        int bytesRead = 0;
//        byte[] buffer = new byte[8192];
//        // 开始向网络传输文件流
//        while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
//            bouts.write(buffer, 0, bytesRead);
//        }
//        bouts.flush();// 这里一定要调用flush()方法
//        ins.close();
//        bins.close();
//        outs.close();
//        bouts.close();
//    }
//
//    /**
//     * 文件下载
//     * @param response
//     * @param path
//     */
//    public void downFile(HttpServletResponse response, String path, String fileName) {
//        try {
//            File file = new File(path);
//            if (file.exists()) {
//                //System.out.println("downloadFile======================path:" + path);
//                //System.out.println("downloadFile======================filename:" + fileName);
//                InputStream ins = new FileInputStream(path);
//
//                downFile(response, ins, fileName);
//
//            } else {
//                //response.sendRedirect("../error.jsp");
//            }
//        } catch (IOException e) {
//            logger.error("文件下载出错", e);
//        }
//    }
}
