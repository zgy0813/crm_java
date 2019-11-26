package com.zgy.qshy.common.base;

/**
 * 常量表
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
public interface BaseConstant {

    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    String EXCEPTION_HEAD = "SOME ERRORS OCCURED!";
    /**
     * 操作名称
     */
    String OPERATION_NAME = "OPERATION_NAME";
    /**
     * 客户端语言
     */
    String USERLANGUAGE = "userLanguage";
    /**
     * 客户端主题
     */
    String WEBTHEME = "webTheme";
    /**
     * 当前用户
     */
    String CURRENT_USER = "CURRENT_USER";
    /**
     * 在线用户数量
     */
    String ALLUSER_NUMBER = "ALLUSER_NUMBER";
    /**
     * 登录用户数量
     */
    String USER_NUMBER = "USER_NUMBER";
    /**
     * 上次请求地址
     */
    String PREREQUEST = "PREREQUEST";
    /**
     * 上次请求时间
     */
    String PREREQUEST_TIME = "PREREQUEST_TIME";
    /**
     * 登录地址
     */
    String LOGIN_URL = "/login.html";
    /**
     * 缓存命名空间
     */
    String CACHE_NAMESPACE = "info:";
    /** 缓存命名空间 */
    String SYSTEM_CACHE_NAMESPACE = "s:info:";

    /** OSS */
    String OSS_KEY = CACHE_NAMESPACE + "oss:";

    /** TOKEN */
    String TOKEN_KEY = "token";

    /** 密码加密方法和hash次数 */
    String HASH_ALGORITH_NAME = "MD5";
    Integer HASH_ITERRATIONS = 1024;

    /** shiro cache */
    String REDIS_SHIRO_CACHE = SYSTEM_CACHE_NAMESPACE + "SHIRO-CACHE:";

    /** SESSION */
    String REDIS_SHIRO_SESSION = SYSTEM_CACHE_NAMESPACE + "SHIRO-SESSION:";

    String KEY_PAGE_NUM                 = "pageIndex";
    String KEY_PAGE_SIZE                = "pageSize";
    String KEY_ORDER_BY                 = "orderBy";

    Integer DEFAULT_QUERY_COUNT         = 10;
    Integer DEFAULT_PARTIAL_QUERY_COUNT = 3;

    /**
     * 非法请求次数
     */
    String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";

    /** 请求报文体 */
    String REQUEST_BODY = "info.requestBody";

    /** swagger 接口类包名*/
    String CONTROLLER_PATH = "com.qshy";
    /** 日志表状态 */
    interface JOBSTATE {
        /**
         * 日志表状态，初始状态，插入
         */
        String INIT_STATS = "I";
        /**
         * 日志表状态，成功
         */
        String SUCCESS_STATS = "S";
        /**
         * 日志表状态，失败
         */
        String ERROR_STATS = "E";
        /**
         * 日志表状态，未执行
         */
        String UN_STATS = "N";
    }

    interface YesOrNo {
        int Yes   = 0;
        int NO    = 1;
    }

}
