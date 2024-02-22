package com.chia.multienty.wechat.thirdparty.define.wxa;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信账号类型
 */
@Getter
@AllArgsConstructor
public enum WxAuthStatus {
    INITIAL(0,"初始状态"),
    EXPIRED(1, "任务超时, 24小时内有效"),
    USER_REJECT(2, "用户授权拒绝"),
    USER_AGREE(3, "用户授权同意"),
    FACE_DETECT_PROGRESS(4, " 发起人脸流程"),
    FACE_DETECT_FAILURE(5, "人脸认证失败"),
    FACE_DETECT_SUCCESS(6, "人脸认证成功"),
    SENT_SMS_AFTER_FACE_DETECT(7, "人脸认证后，已经提交手机号码下发验证码"),
    PHONE_VERIFICATION_FAILURE(8, "手机验证失败"),
    PHONE_VERIFICATION_SUCCESS(9, "手机验证成功"),
    CERTIFICATION_REVIEW_FORM_CREATE_FAILED(11, "创建认证审核单失败"),
    CERTIFICATION_REVIEW_FORM_CREATE_SUCCESS(12, "创建认证审核单成功"),
    VERIFICATION_FAILED(14, "验证失败"),
    WAIT_PAY(15,"等待支付")
    ;
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static WxAuthStatus valueOf(Integer value) {
        if(value == null) {
            return null;
        }
        for (WxAuthStatus type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
