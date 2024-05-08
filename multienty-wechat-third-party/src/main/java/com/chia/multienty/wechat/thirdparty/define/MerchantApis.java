package com.chia.multienty.wechat.thirdparty.define;

public class MerchantApis {


    /**
     * 注册小程序
     */
    public class Register {
        /**
         * 快速注册企业小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/register-management/fast-registration-ent/registerMiniprogram.html">微信官方文档 - 快速注册企业小程序</a>
         */
        public static final String REG_ENTERPRISE_MINI_PROGRAM = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=create&component_access_token=%s";

        /**
         * 复用公众号主体快速注册小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/register-management/fast-registration-officalaccount/registerMiniprogramByOffiaccount.html">微信官方文档 - 复用公众号主体快速注册小程序</a>
         */
        public static final String REG_MINI_PROGRAM_BY_OFFICIAL_ACCOUNT = "https://api.weixin.qq.com/cgi-bin/account/fastregister?access_token=%s";

        /**
         * 修改试用小程序名称
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/register-management/fast-regist-beta/modifyBetaMiniprogramNickName.html">微信官方文档 - 修改试用小程序名称</a>
         */
        public static final String MODIFY_BETA_MINI_PROGRAM_NICK_NAME = "https://api.weixin.qq.com/wxa/setbetaweappnickname?access_token=%s";

        /**
         * 试用小程序快速转正
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/register-management/fast-regist-beta/verfifyBetaMiniprogram.html">微信官方文档 - 试用小程序快速认证</a>
         */
        public static final String VERIFY_BETA_MINI_PROGRAM = "https://api.weixin.qq.com/wxa/verifybetaweapp?access_token=%s";

        /**
         * 注册试用小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/register-management/fast-regist-beta/registerBetaMiniprogram.html">微信官方文档 - 注册试用小程序</a>
         */
        public static final String REG_BETA_MINI_PROGRAM = "https://api.weixin.qq.com/wxa/component/fastregisterbetaweapp?access_token=%s";

    }

    /**
     * 基础信息
     */
    public class BaseInfo {
        /**
         * 小程序登录
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/login/thirdpartyCode2Session.html">微信官方文档 - 小程序登录</a>
         */
        public static final String LOGIN = "https://api.weixin.qq.com/sns/component/jscode2session?component_access_token=%s";

        /**
         * 获取基本信息
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/getAccountBasicInfo.html">微信官方文档 - 获取基本信息</a>
         */
        public static final String GET_BASE_INFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo?access_token=%s";

        /**
         * 查询绑定的开放平台账号
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/getBindOpenAccount.html">微信官方文档 - 查询绑定的开放平台账号</a>
         */
        public static final String QUERY_BIND_OPEN_ACCOUNT = "https://api.weixin.qq.com/cgi-bin/open/have?access_token=%s";

        /**
         * 小程序名称检测
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/checkNickName.html">微信官方文档 - 小程序名称检测</a>
         */
        public static final String CHECK_NICK_NAME = "https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname?access_token=%s";

        /**
         * 设置小程序名称
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/setNickName.html">微信官方文档 - 设置小程序名称</a>
         */
        public static final String SET_NICK_NAME = "https://api.weixin.qq.com/wxa/setnickname?access_token=%s";

        /**
         * 查询小程序名称审核状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/getNickNameStatus.html">微信官方文档 - 查询小程序名称审核状态</a>
         */
        public static final String GET_NICK_NAME_STATUS = "https://api.weixin.qq.com/wxa/api_wxa_querynickname?access_token=%s";

        /**
         * 设置小程序介绍
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/setSignature.html">微信官方文档 - 设置小程序介绍</a>
         */
        public static final String SET_SIGNATURE = "https://api.weixin.qq.com/cgi-bin/account/modifysignature?access_token=%s";

        /**
         * 获取搜索状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/getSearchStatus.html">微信官方文档 - 获取搜索状态</a>
         */
        public static final String GET_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/getwxasearchstatus?access_token=%s";

        /**
         * 设置搜索状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/setSearchStatus.html">微信官方文档 - 设置搜索状态</a>
         */
        public static final String SET_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/changewxasearchstatus?access_token=%s";

        /**
         * 修改头像
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/setHeadImage.html">微信官方文档 - 修改头像</a>
         */
        public static final String SET_HEAD_IMAGE = "https://api.weixin.qq.com/cgi-bin/account/modifyheadimage?access_token=%s";


    }

    /**
     * 小程序微信认证
     */
    public class WxAuth {
        /**
         * 小程序认证
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/weapp-wxverify/secwxaapi_wxaauth.html">微信官方文档 - 小程序认证</a>
         */
        public static final String AUTH_MINI_PROGRAM = "https://api.weixin.qq.com/wxa/sec/wxaauth?access_token=%s";

        /**
         * 小程序认证进度查询
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/weapp-wxverify/secwxaapi_queryauth.html">微信官方文档 - 小程序认证进度查询</a>
         */
        public static final String QUERY_MINI_PROGRAM_PROGRESS = "https://api.weixin.qq.com/wxa/sec/queryauth?access_token=%s";

        /**
         * 小程序认证上传补充材料
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/weapp-wxverify/secwxaapi_uploadauthmaterial.html">微信官方文档 - 小程序认证上传补充材料</a>
         */
        public static final String UPLOAD_AUTH_MATERIAL = "https://api.weixin.qq.com/wxa/sec/uploadauthmaterial?access_token=%S";

        /**
         * 小程序认证重新提审
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/weapp-wxverify/secwxaapi_reauth.html">微信官方文档 - 小程序认证重新提审</a>
         */
        public static final String RE_AUTH_MINI_PROGRAM = "https://api.weixin.qq.com/wxa/sec/reauth?access_token=%s";
    }

    /**
     * 小程序域名管理
     */
    public class Domain {
        /**
         * 配置小程序服务器域名
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/modifyServerDomain.html">微信官方文档 - 配置小程序服务器域名</a>
         */
        public static final String MODIFY_SERVER_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain?access_token=%s";

        /**
         * 配置小程序业务域名
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/modifyJumpDomain.html">微信官方文档 - 配置小程序业务域名</a>
         */
        public static final String MODIFY_JUMP_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=%s";

        /**
         * 快速配置小程序服务器域名
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/modifyServerDomainDirectly.html">微信官方文档 - 快速配置小程序服务器域名</a>
         */
        public static final String MODIFY_SERVER_DOMAIN_DIRECTLY = "https://api.weixin.qq.com/wxa/modify_domain_directly?access_token=%s";

        /**
         * 获取业务域名校验文件
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/getJumpDomainConfirmFile.html">微信官方文档 - 获取业务域名校验文件</a>
         */
        public static final String GET_JUMP_DOMAIN_CONFIRM_FILE = "https://api.weixin.qq.com/wxa/get_webviewdomain_confirmfile?access_token=%s";

        /**
         * 快速配置小程序业务域名
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/modifyJumpDomainDirectly.html">微信官方文档 - 快速配置小程序业务域名</a>
         */
        public static final String MODIFY_JUMP_DOMAIN_DIRECTLY = "https://api.weixin.qq.com/wxa/setwebviewdomain_directly?access_token=%s";

        /**
         * 获取发布后生效服务器域名列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/getEffectiveServerDomain.html">微信官方文档 - 获取发布后生效服务器域名列表</a>
         */
        public static final String GET_EFFECTIVE_SERVER_DOMAIN = "https://api.weixin.qq.com/wxa/get_effective_domain?access_token=%s";

        /**
         * 获取发布后生效业务域名列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/domain-management/getEffectiveJumpDomain.html">微信官方文档 - 获取发布后生效业务域名列表</a>
         */
        public static final String GET_EFFECTIVE_JUMP_DOMAIN = "https://api.weixin.qq.com/wxa/get_effective_webviewdomain?access_token=%s";
    }
    /**
     * 小程序类目管理
     */
    public class Category {
        /**
         * 获取可设置的所有类目
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/category-management/getAllCategories.html">微信官方文档 - 获取可设置的所有类目</a>
         */
        public static final String GET_ALL_CATEGORIES = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories?access_token=%s";

        /**
         * 获取已设置的所有类目
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/category-management/getSettingCategories.html">微信官方文档 - 获取已设置的所有类目</a>
         */
        public static final String GET_SETTING_CATEGORIES = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategory?access_token=%s";

        /**
         * 获取不同类型主体可设置的类目
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/category-management/getAllCategoriesByType.html">微信官方文档 - 获取不同类型主体可设置的类目</a>
         */
        public static final String GET_ALL_CATEGORIES_BY_TYPE = "https://api.weixin.qq.com/cgi-bin/wxopen/getcategoriesbytype?access_token=%s";

        /**
         * 添加类目
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/category-management/addCategory.html">微信官方文档 - 添加类目</a>
         */
        public static final String ADD_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/addcategory?access_token=%s";

        /**
         * 删除类目
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/category-management/deleteCategory.html">微信官方文档 - 删除类目</a>
         */
        public static final String DELETE_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory?access_token=%s";
    }

    /**
     * 地理位置接口申请
     */
    public class GEO {
        /**
         * 申请地理位置接口
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/privacy-api-management/applyPrivacyInterface.html">微信官方文档 - 申请地理位置接口</a>
         */
        public static final String APPLY_PRIVACY_INTERFACE = "https://api.weixin.qq.com/wxa/security/apply_privacy_interface?access_token=%s";

        /**
         * 获取地理位置接口列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/privacy-api-management/getPrivacyInterface.html">微信官方文档 - 获取地理位置接口列表</a>
         */
        public static final String GET_PRIVACY_INTERFACE = "https://api.weixin.qq.com/wxa/security/get_privacy_interface?access_token=%s";
    }

    /**
     * 小程序成员管理
     */
    public class Member {
        /**
         * 绑定体验者
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/member-management/bindTester.html">微信官方文档 - 绑定体验者</a>
         */
        public static final String BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester?access_token=%s";

        /**
         * 解除绑定体验者
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/member-management/unbindTester.html">微信官方文档 - 解除绑定体验者</a>
         */
        public static final String UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester?access_token=%s";

        /**
         * 获取体验者列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/member-management/getTester.html">微信官方文档 - 获取体验者列表</a>
         */
        public static final String GET_TESTER = "https://api.weixin.qq.com/wxa/memberauth?access_token=%s";
    }

    public class Code {
        /**
         * 上传代码并生成体验版
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/commit.html">微信官方文档 - 上传代码并生成体验版</a>
         */
        public static final String COMMIT = "https://api.weixin.qq.com/wxa/commit?access_token=%s";

        /**
         * 获取已上传的代码页面列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getCodePage.html">微信官方文档 - 获取已上传的代码页面列表</a>
         */
        public static final String GET_CODE_PAGE = "https://api.weixin.qq.com/wxa/get_page?access_token=%s";

        /**
         * 获取体验版二维码
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getTrialQRCode.html">微信官方文档 - 获取体验版二维码</a>
         */
        public static final String GET_TRIAL_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=%s";

        /**
         * 提交代码审核
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/submitAudit.html">微信官方文档 - 提交代码审核</a>
         */
        public static final String SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit?access_token=%s";

        /**
         * 查询审核单状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getAuditStatus.html">微信官方文档 - 查询审核单状态</a>
         */
        public static final String GET_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_auditstatus?access_token=%s";

        /**
         * 撤回代码审核
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/undoAudit.html">微信官方文档 - 撤回代码审核</a>
         */
        public static final String UNDO_AUDIT = "https://api.weixin.qq.com/wxa/undocodeaudit?access_token=%s";

        /**
         * 发布已通过审核的小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/release.html">微信官方文档 - 发布已通过审核的小程序</a>
         */
        public static final String RELEASE = "https://api.weixin.qq.com/wxa/release?access_token=%s";

        /**
         * 小程序版本回退
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/revertCodeRelease.html">微信官方文档 - 小程序版本回退</a>
         */
        public static final String REVERT_CODE_RELEASE = "https://api.weixin.qq.com/wxa/revertcoderelease?";

        /**
         * 分阶段发布
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/grayRelease.html">微信官方文档 - 分阶段发布</a>
         */
        public static final String GRAY_RELEASE = "https://api.weixin.qq.com/wxa/grayrelease?access_token=%s";

        /**
         * 获取分阶段发布详情
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getGrayReleasePlan.html">微信官方文档 - 获取分阶段发布详情</a>
         */
        public static final String GET_GRAY_RELEASE_PLAN = "https://api.weixin.qq.com/wxa/getgrayreleaseplan?access_token=%s";

        /**
         * 设置小程序服务状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/setVisitStatus.html">微信官方文档 - 设置小程序服务状态</a>
         */
        public static final String SET_VISIT_STATUS = "https://api.weixin.qq.com/wxa/change_visitstatus?access_token=%s";

        /**
         * 取消分阶段发布
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/revertGrayRelease.html">微信官方文档 - 取消分阶段发布</a>
         */
        public static final String REVERT_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/revertgrayrelease?access_token=%s";

        /**
         * 查询小程序服务状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getVisitStatus.html">微信官方文档 - 查询小程序服务状态</a>
         */
        public static final String GET_VISIT_STATUS = "https://api.weixin.qq.com/wxa/getvisitstatus?access_token=%s";

        /**
         * 查询各版本用户占比
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getSupportVersion.html">微信官方文档 - 查询各版本用户占比</a>
         */
        public static final String GET_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion?access_token=%s";

        /**
         * 设置最低基础库版本
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/setSupportVersion.html">微信官方文档 - 设置最低基础库版本</a>
         */
        public static final String SET_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion?access_token=%s";

        /**
         * 查询服务商审核额度
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/setCodeAuditQuota.html">微信官方文档 - 查询服务商审核额度</a>
         */
        public static final String SET_CODE_AUDIT_QUOTA = "https://api.weixin.qq.com/wxa/queryquota?access_token=%s";
        /**
         * 加急代码审核
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/speedupCodeAudit.html">微信官方文档 - 加急代码审核</a>
         */
        public static final String SPEED_UP_CODE_AUDIT = "https://api.weixin.qq.com/wxa/speedupaudit?access_token=%s";
        /**
         * 查询小程序版本信息
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getVersionInfo.html">微信官方文档 - 查询小程序版本信息</a>
         */
        public static final String GET_VERSION_INFO = "https://api.weixin.qq.com/wxa/getversioninfo?access_token=%s";
        /**
         * 查询最新一次审核单状态
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getLatestAuditStatus.html">微信官方文档 - 查询最新一次审核单状态</a>
         */
        public static final String GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus?access_token=%s";
        /**
         * 上传提审素材
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/uploadMediaToCodeAudit.html">微信官方文档 - 上传提审素材</a>
         */
        public static final String UPLOAD_MEDIA_TO_CODE_AUDIT = "https://api.weixin.qq.com/wxa/uploadmedia?access_token=%s";
        /**
         * 获取隐私接口检测结果
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/code-management/getCodePrivacyInfo.html">微信官方文档 - 获取隐私接口检测结果</a>
         */
        public static final String GET_CODE_PRIVACY_INFO = "https://api.weixin.qq.com/wxa/security/get_code_privacy_info?access_token=%s";
    }

    /**
     * 公众号
     */
    public class OfficialAccount {
        /**
         * 新增临时素材
         * @see <a href="https://developers.weixin.qq.com/doc/offiaccount/Asset_Management/New_temporary_materials.html">微信官方文档 - 新增临时素材</a>
         */
        public static final String ADD_TEMP_ASSET = "https://api.weixin.qq.com/cgi-bin/media/upload?";
        /**
         * 获取已设置公众号信息
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/subscribe-component/getShowItem.html">微信官方文档 - 获取已设置公众号信息</a>
         */
        public static final String GET_SHOW_ITEM = "https://api.weixin.qq.com/wxa/getshowwxaitem?access_token=%s";
        /**
         * 获取可设置公众号列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/subscribe-component/getLinkForShow.html">微信官方文档 - 获取可设置公众号列表</a>
         */
        public static final String GET_LINK_FOR_SHOW = "https://api.weixin.qq.com/wxa/getwxamplinkforshow?access_token=%s";

        /**
         * 设置扫码关注的公众号
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/subscribe-component/setShowItem.html">微信官方文档 - 设置扫码关注的公众号</a>
         */
        public static final String SET_SHOW_ITEM = "https://api.weixin.qq.com/wxa/updateshowwxaitem?access_token=%s";
        /**
         * 获取公众号关联的小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/link-miniprogram/getLinkMiniprogram.html">微信官方文档 - 获取公众号关联的小程序</a>
         */
        public static final String GET_LINK_MINI_PROGRAM = "https://api.weixin.qq.com/cgi-bin/wxopen/wxamplinkget?access_token=%s";
        /**
         * 公众号关联小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/link-miniprogram/linkMiniprogram.html">微信官方文档 - 公众号关联小程序</a>
         */
        public static final String LINK_MINI_PROGRAM = "https://api.weixin.qq.com/cgi-bin/wxopen/wxamplink?access_token=%s";
        /**
         * 公众号解除关联小程序
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/link-miniprogram/unlinkMiniprogram.html">微信官方文档 - 公众号解除关联小程序</a>
         */
        public static final String UNLINK_MINI_PROGRAM = "https://api.weixin.qq.com/cgi-bin/wxopen/wxampunlink?access_token=%s";
        /**
         * 获取已设置的二维码规则
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/qrcode-config/getJumpQRCode.html">微信官方文档 - 获取已设置的二维码规则</a>
         */
        public static final String GET_JUMP_QRCODE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpget?access_token=%s";
        /**
         * 增加或修改二维码规则
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/qrcode-config/addJumpQRCode.html">微信官方文档 - 增加或修改二维码规则</a>
         */
        public static final String ADD_JUMP_QRCODE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpadd?access_token=%s";
        /**
         * 发布已设置的二维码规则
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/qrcode-config/publishJumpQRCode.html">微信官方文档 - 发布已设置的二维码规则</a>
         */
        public static final String PUBLISH_JUMP_QRCODE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumppublish?access_token=%s";
        /**
         * 删除已设置的二维码规则
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/officalaccount-management/qrcode-config/deleteJumpQRCode.html">微信官方文档 - 删除已设置的二维码规则</a>
         */
        public static final String DELETE_JUMP_QRCODE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpdelete?access_token=%s";
    }

    /**
     * 物流组件
     */
    public class Express {
        /**
         * 申请开通物流消息
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/logistics-service/applyMsgPlugin.html">微信官方文档 - 申请开通物流消息</a>
         */
        public static final String APPLY_MSG_PLUGIN = "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/open_openmsg?access_token=%s";
        /**
         * 申请开通物流退货组件
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/logistics-service/applyReturnPlugin.html">微信官方文档 - 申请开通物流退货组件</a>
         */
        public static final String APPLY_RETURN_PLUGIN = "https://api.weixin.qq.com/cgi-bin/express/delivery/return/open_return?access_token=%s";

        /**
         * 申请开通物流查询组件
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/logistics-service/applyQueryPlugin.html">微信官方文档 - 申请开通物流查询组件</a>
         */
        public static final String APPLY_QUERY_PLUGIN = "https://api.weixin.qq.com/cgi-bin/express/delivery/open_msg/open_query_plugin?access_token=%s";

    }
}
