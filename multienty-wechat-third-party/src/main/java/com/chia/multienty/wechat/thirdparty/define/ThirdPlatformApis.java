package com.chia.multienty.wechat.thirdparty.define;

public class ThirdPlatformApis {
    public class Authorizing {

        /**
         * 启动票据推送服务
         */
        public static final String START_PUSH_TICKET = "https://api.weixin.qq.com/cgi-bin/component/api_start_push_ticket";

        public static final String GET_COMPONENT_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

        /**
         * 使用授权码获取授权信息
         */
        public static final String GET_AUTHORIZATION_INFO_BY_AUTH_CODE = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%s";
        /**
         * 获取授权账号调用令牌
         */
        public static final String GET_AUTHORIZER_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=%s";

        /**
         * 获取刷新令牌
         */
        public static final String GET_AUTHORIZER_REFRESH_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?access_token=%s";
        /**
         * 获取预授权码
         */
        public static final String GET_PRE_AUTH_CODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?access_token=%s";
        /**
         * 拉取已授权的账号信息
         */
        public static final String GET_AUTHORIZER_LIST = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_list?access_token=%s";
        /**
         * 获取授权账号详情
         */
        public static final String GET_AUTHORIZER_INFO = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?access_token=%s";

        /**
         * 设置授权方选项信息
         */
        public static final String SET_AUTHORIZER_OPTION_INFO = "https://api.weixin.qq.com/cgi-bin/component/set_authorizer_option?access_token=%s";

        /**
         * 获取授权方选项信息
         */
        public static final String GET_AUTHORIZER_OPTION_INFO = "https://api.weixin.qq.com/cgi-bin/component/get_authorizer_option?access_token=%s";
    }

    /**
     * 模板库管理
     */
    public class Template {
        /**
         * 获取草稿箱列表
         * @see <a href="https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/thirdparty-management/template-management/getTemplatedRaftList.html">微信官方文档 - 获取草稿箱列表</a>
         */
        public static final String GET_TEMPLATED_RAFT_LIST = "https://api.weixin.qq.com/wxa/gettemplatedraftlist?access_token=%s";
        /**
         * 将草稿添加到模板库
         */
        public static final String ADD_TO_TEMPLATE = "https://api.weixin.qq.com/wxa/addtotemplate?access_token=%s";
        /**
         * 获取模板列表
         */
        public static final String GET_TEMPLATE_LIST = "https://api.weixin.qq.com/wxa/gettemplatelist?access_token=%s";
        /**
         * 删除代码模板
         */
        public static final String DELETE_TEMPLATE = "https://api.weixin.qq.com/wxa/deletetemplate?access_token=%s";
    }

    /**
     * 域名管理
     */
    public class Domain {
        /**
         * 设置第三方平台服务器域名
         */
        public static final String MODIFY_THIRD_PARTY_SERVER_DOMAIN = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_server_domain?access_token=%S";
        /**
         * 获取第三方平台业务域名校验文件
         */
        public static final String GET_THIRD_PARTY_JUMP_DOMAIN_CONFIRM_FILE = "https://api.weixin.qq.com/cgi-bin/component/get_domain_confirmfile?access_token=%s";
        /**
         * 设置第三方平台业务域名
         */
        public static final String MODIFY_THIRD_PARTY_JUMP_DOMAIN = "https://api.weixin.qq.com/cgi-bin/component/modify_wxa_jump_domain?access_token=%s";
    }
}
