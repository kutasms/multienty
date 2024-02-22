
package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * HTTP请求返回结果枚举
 */
@Getter
@AllArgsConstructor
public enum HttpResultEnum {

    SUCCESS(200,"成功"),
    RESPONSE_TYPE_ERROR(501, "响应类型错误"),
    UNEXPECTED_ARG_TYPE_ERROR(601, "参数校验类型不匹配"),
    CONSTRAINT_VIOLATION(602, "方法RequestParam/PathVariable形式参数校验异常"),
    METHOD_ARG_NOT_VALID(603, "参数校验不通过"),
    BIND_EXCEPTION(604, "提交参数校不符合规则"),
    ACCOUNT_NOT_PERMITTED(1000,"账号未被许可"),
    SYSTEM_ERROR(1001,"系统异常,请稍后再试"),
    SYSTEM_HINT(1002,"系统提示信息"),
    SYSTEM_RUNTIME_ERROR(1003,"服务器运行异常"),
    SYSTEM_NULL_ERROR(1004,"服务器出现空值异常,请联系相关技术人员"),
    SYSTEM_CONVERT_ERROR(1005, "数据类型转换异常"),
    SYSTEM_IO_ERROR(1006,"服务器出现文件类型错误,请联系相关技术人员"),
    SYSTEM_NETWORK_ERROR(1007, "网络异常"),

    SYSTEM_MP4_FILE_MAXIMUM_50M(1008, "视频文件最大50M"),
    SYSTEM_IMG_FILE_MAXIMUM_2M(1009,"图片文件最大支持2M"),
    SYSTEM_DICT_CONFIG_ERROR(1010, "字典配置错误"),
    SYSTEM_DATABASE_ERROR(1011, "数据保存错误"),
    SYSTEM_DATA_EMPTY(1012,"找不到相关数据"),
    NOT_SUPPORT_OPERATION(1013, "您的状态不支持执行此操作"),
    SYSTEM_STATUS_ERROR(1014, "状态错误, 不允许执行此操作"),
    THIRD_PARTY_EXCEPTION(1015, "第三方接口异常"),
    NOT_SETTING_NOTIFY_URL(1016,"未设置回调URL"),
    HTTP_BODY_RESOLVING_ERROR(1017, "http请求内容解析错误"),
    ENUM_ITEM_NOT_FOUND(1018, "无法找到枚举元素"),
    DATA_CANNOT_DELETE(1019, "该数据不允许删除"),
    PRIMARY_KEY_NULL(1020, "主键字段为空"),

    DATA_UPDATE_FAILURE(1021, "数据更新失败"),
    DATA_DELETE_FAILURE(1022, "数据删除失败"),
    PAGE_SIZE_EXCEEDED_100(1023, "数据页大小超出100"),
    PAGE_SIZE_EXCEEDED_50(1024, "数据页大小超出50"),
    CITY_SELECT_PROVINCE_NOT_NULL(2001,"城市查询时必须包含省份信息"),
    PARAMETER_LOSE(11001, "必填参数不能为空"),
    PARAMETER_INVALID(11002,"非法参数"),
    PARAMETER_PRIMARY_KEY_LOSE(11003,"主键数据丢失"),

    REPEAT_REQUEST(11004, "重复请求,请稍后再试"),
    NOT_FILE(11005, "文件为空,请重新选择文件"),
    ARGUMENT_ERROR(11006, "参数错误"),
    ILLEGAL_OPERATION(11007, "非法操作"),
    SERVICE_BUSY(11008, "服务繁忙,请稍后再试"),
    MISS_DATA(11009, "缺少数据%s"),
    ILLEGAL_ARG_PATTERN(11010, "非法参数%s"),
    ARG_NOT_CHANGED_PATTERN(11011,"参数%s未更改，请再次确认!"),
    ARG_IS_NULL_PATTERN(11012, "参数%s为空，请再次确认!"),
    ARG_ERROR_PATTERN(11013, "参数%s错误"),
    ARG_LOSE_PATTERN(11014, "参数%s丢失"),
    HEADER_LOSE_PATTERN(11015,"请求头%s丢失"),
    COLLECTION_IS_EMPTY_PATTERN(11016, "%s列表不能为空"),
    BASIC_DICT_NAME_EXISTS(12001,"字典名称已存在,请修改后重试"),
    DICT_OPERATION_LIMITED(12002,"系统级字典配置无法更改或删除"),
    DEMO_ACCOUNT_OPERATION_LIMITED(12003,"演示账号无法编辑数据"),
    BASIC_IP_EXISTS(12004,"IP地址已存在"),
    VIDEO_FILE_MAX_2M(12005,"视频文件不能超过2M"),
    KEYWORD_EXISTS(12006, "关键词已存在"),
    TOKEN_CANNOT_BE_NULL(20003, "授权码不能为空"),
    TOKEN_APPROVE_ERROR(20004, "授权失败"),
    USER_NOT_LOGIN(20005,"请先登陆系统"),
    USER_LOGGED_IN(20006,"用户已登录"),
    USERNAME_OR_MOBILE_EXISTS(20007,"该用户名或手机号已注册"),
    VERIFICATION_CODE_SEND_FAILURE(20008,"验证码发送失败"),
    VERIFICATION_CODE_EXPIRED(20009,"验证码已过期"),
    TOKEN_VALIDATE_FAILURE(20010, "用户凭证验证失败"),
    TOKEN_EXPIRED(20011, "用户凭证已过期"),

    VERIFICATION_CODE_ERROR(20012,"验证码错误"),
    DUPLICATE_PHONE_NUMBER(20013, "此手机号码已注册"),
    ILLEGAL_TOKEN(20014, "非法令牌"),
    UN_REGISTERED_PHONE_NUMBER(20015, "未注册的手机号码"),
    EXCEEDING_LOGIN_FAILURE_THRESHOLD(20016, "超出登录错误阈值"),

    LOGIN_APPLICATION_FAILURE(20017, "登录平台编号错误"),
    DISALLOWED_LOGIN_MODE(20018, "不允许的登录模式"),
    REVIEW_FAIL_NEED_REASON(20019, "当审核为失败时，请填写失败原因"),
    USER_OLD_PASSWORD_ERROR(30001,"原密码错误"),
    USER_SMS_LOCKING(30002,"连续发送短信导致用户短信发送状态锁定"),
    USER_PAYMENT_PASSWORD_ERROR(30003, "支付密码输入错误"),
    USER_PAYMENT_PASSWORD_NOT_SET(30004,"请先设置支付密码"),
    USER_ACCOUNT_LOCKED(30005,"连续多次输入密码出错,账号已锁定"),
    USER_NOT_REGISTERED(30006,"用户未注册"),
    USER_ALREADY_REGISTERED(30007, "该用户已注册"),
    USER_NAME_OR_PASSWORD_ERROR(30008, "用户名或者密码错误"),
    USER_LOGGED_OFF(30009,"该用户已被注销"),
    USER_STATE_DISABLED(30010,"该用户已被限制使用"),
    USER_RESIGNED(30011,"用户已离职"),
    USER_NOT_ENABLED(30012, "该用户未处于启用状态，不能停用"),
    USER_NOT_DISABLED(30013, "该用户未处于停用状态,不能启用"),
    USER_MOBILE_NUMBER_ALREADY_BOUND_SHOP(30014,"该手机号已绑定店铺"),
    USER_MOBILE_NUMBER_ALREADY_BOUND_INNER_ACCOUNT(30015,"该手机号码已绑定内部账号，无法完成此操作"),
    USER_VALIDATION_ERROR(30016,"用户身份效验失败"),
    USER_PASSWORD_INCONSISTENT(30017,"两次输入密码不一致"),
    WECHAT_AUTHORIZATION_FAILURE(30018,"微信授权失败"),

    USER_IN_BLACK_LIST(30019,"您已被拉入黑名单"),
    USER_BANK_CARD_EXISTS(30020,"此银行卡已存在，请更换其他银行卡"),
    ALIPAY_MINI_APP_AUTHORIZATION_FAILURE(30021,"支付宝小程序授权失败"),
    USER_MOBILE_NUMBER_VALIDATION_FAILURE(30022,"手机号码验证失败"),
    USER_PAYMENT_PASSWORD_UPDATE_CODE_EXPIRED(30024,"本次支付密码修改的验证码已过期,请重试发送验证短信"),
    USER_MOBILE_NUMBER_EXISTS(30028,"此手机号码已被其他用户使用"),
    USER_MOBILE_NUMBER_BOUND_BY_OTHER_WEIXIN(30029,"此手机号码已绑定其他微信账号"),

    SUPER_ADMIN_CANNOT_BE_DELETED(30030, "超级管理员不能被删除"),
    LEAST_ONE_SUPER_ADMIN(30031, "至少保留一个超级管理员帐号"),
    CUSTOMER_PHONE_EMPTY(30032,"客户手机号为空"),
    ROLE_NON_EDITABLE(31001,"此角色不允许编辑"),
    ROLE_CANNOT_BE_DELETED(31002, "此角色不允许删除"),
    ORDER_LINKED_ADDRESS_PROCESSING(40001, "该地址关联的订单正在处理中"),
    ORDER_BALANCE_NOT_POSITIVE(40002,"您的余额非正数，不允许下单"),
    ORDER_REJECTED_BY_RISK_CONTROL(40003, "订单因触发风控而被拒绝"),
    ORDER_NOT_BINDING_WEIXIN(40004,"客户未绑定微信,无法使用微信支付"),
    ORDER_PAY_MONEY_AMOUNT_CANNOT_BE_ZERO(40005,"支付金额不能为0"),
    ORDER_COLLECTION_CODE_GENERATING_FAILURE(40006,"生成收款码失败"),
    ORDER_PAYMENT_FAILURE(40007,"订单预支付失败"),
    ORDER_BALANCE_NOT_ENOUGH(40008,"余额不足以支付订单"),
    ORDER_EXPENSE_AMOUNT_MORE_THAN_PAY(40009, "优惠金额不能大于等于支付金额"),

    ORDER_STATE_CHANGED_CANNOT_CONTINUE(40011,"该订单状态已变更,无法进行该操作"),
    ORDER_TRANSFER_DAY_LIMIT(40012, "订单转移的日期只能小于结束日期并且大于开始日期"),

    AFTER_SHOP_BALANCE_INSUFFICIENT(41001,"店铺可用退款余额不足"),
    AFTER_REFUND_APPLY_FOR_FAILURE(41002,"退款申请失败"),
    AFTER_STATE_UPDATED_CANNOT_PROCESS_REQUEST(41003,"该售后单状态已变更,无法进行该操作"),
    AFTER_EXISTS_NOT_COMPLETED(41005,"此订单还有未完成的售后任务"),
    AFTER_ORDER_REVIEWED(41006,"订单已审核，无法进行其他操作"),


    PROMOTION_NEXT_RANK_NOT_BIGGER(70001,"下一级应比上一级数值更大"),
    PROMOTION_CANNOT_CREATE_RELATIONSHIP_WITH_PROMOTER(70002,"分销员之间不允许建立关系"),
    PROMOTION_PROTECTION_PERIOD_INVALID(70003,"保护期时间需要小于有效期"),
    PROMOTION_MOBILE_NUMBER_EXISTS(70004,"该手机号已绑定分销员"),
    PROMOTION_INVITEE_CANNOT_BE_SELF(70005,"邀请人不能是自己"),
    PROMOTION_USER_MUST_CUSTOMER(70006,"该用户并非平台会员"),
    PROMOTION_USER_DISABLED(70007,"该用户已停用"),
    PROMOTION_USER_IN_BLACK_LIST(70008,"该用户已被拉入黑名单"),
    PROMOTION_NOT_SET_RANK(70009,"未设置分销员等级"),
    PROMOTION_CANNOT_ROBBING_CUSTOMER(70010,"当前关系设置不允许抢客"),
    PROMOTION_RANK_EXISTS(70011,"已设置相同分销员等级"),

    HOSPITAL_DISABLED(71001, "此医院已不再提供服务"),
    DEPARTMENT_DISABLED(72001,"此科室已不再提供服务"),

    ORDER_TIME_CONFLICT(73001,"此服务人员在指定时间段已有预约"),

    CAREGIVERS_UPDATE_ERROR(74001, "护工状态已变更，操作失败"),
    CAREGIVER_HAS_NOT_COMPLETED_ORDER(74002,"您还有未完成的订单"),
    CAREGIVER_DATE_CONFLICT(74003,"护工时间冲突,可能已被其他客户预约"),

    PRICE_STRATEGY_MUST_HAVE_UNIVERSAL(75001, "价格策略中须包含通用价格"),
    PRICE_STRATEGY_KEEP_LEAST_ONE(75002, "同一医院至少保留一个价格策略"),
    PRICE_STRATEGY_CANNOT_DELETE_IMMEDIATELY(75003,"生效中的价格策略不能直接删除,请先禁用"),

    SETTLEMENT_STRATEGY_CANNOT_DELETE_IMMEDIATELY(76001,"生效中的结佣策略不能直接删除,请先禁用"),
    SETTLEMENT_STRATEGY_KEEP_LEAST_ONE(76002, "同一医院至少保留一个结佣策略"),

    OPERATOR_MUST_HAVE_HOSPITAL(77001, "您当前尚未设置医院，请联系公司管理员"),

    ;



    private Integer code;
    private String message;

    public static String parse(Integer code) {
        HttpResultEnum httpResultEnum = Arrays.stream(HttpResultEnum.values()).filter(p -> p.getCode().equals(code)).findFirst().orElse(null);
        if(httpResultEnum != null) {
            return httpResultEnum.getMessage();
        }
        return null;
    }
}
