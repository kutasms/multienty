package com.chia.multienty.core.domain.constants;

public class MultientyConstants {
    /**
     * 分片环境FLYWAY组件的SQL文件存放地址
     */
    public static final String SHARDING_FLYWAY_SQL_DIR = "sharding/";
    /**
     * 分片数据源
     */
    public static final String DS_SHARDING = "sharding";
    /**
     * master数据源
     */
    public static final String DS_MASTER = "master";
    /**
     * ROOT数据源
     */
    public static final String DS_ROOT = "root";
    /**
     * 参数
     * */
    public static final String PARAM = "param";

    /**
     * 默认缓存方法键前缀
     * */
    public static final String DEFAULT_CACHE_METHOD_KEY_PREFIX = "DCMKP";

    /**
     * 拥有者 - 平台
     */
    public static final long OWNER_PLATFORM = 1;

    /**
     * REQUEST缓存用户键
     */
    public static final String REQ_ATTR_USER_KEY = "user";

    /**
     * 平台的应用类型（所有系统以1为总平台的应用类型）
     */
    public static final Integer APPLICATION_TYPE_PLATFORM = 1;

    /**
     * 登录验证码缓存键
     */
    public static final String LOGIN_VERIFY_CODE_CACHE_KEY = "LOG-VER-CODE-%s";

    public static final String TENANT_LOGIN_VERIFY_CODE_CACHE_KEY = "TEN-LOG-VER-CODE-%s";
    /**
     * 登录验证码缓存毫秒数
     */
    public static final long LOGIN_VERIFY_CODE_CACHE_MILLS = 1000 * 60 * 5;

    /**
     * 登录失败计数缓存键
     */
    public static final String LOGIN_FAILURE_COUNT_CACHE_KEY = "LOG_FAIL_%s_%s";

    /**
     * SMS服务BEAN注册前缀
     */
    public static final String SMS_SERVICE_BEAN_PREFIX = "SMS_SVC_";
    /**
     * 支付服务BEAN注册前缀
     */
    public static final String PAY_SERVICE_BEAN_PREFIX = "PAY_SVC_";
    /**
     * 文件上传服务BEAN注册前缀
     */
    public static final String FILE_UPLOAD_SERVICE_BEAN_PREFIX = "FILE_UP_SVC_";
    /**
     * 默认头像地址
     */
    public static final String DEFAULT_AVATAR = "https://kutashop.cn/img/chia.png";
}
