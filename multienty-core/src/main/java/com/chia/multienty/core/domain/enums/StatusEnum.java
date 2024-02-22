package com.chia.multienty.core.domain.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 * */
@ApiModel(value = "StatusEnum", description = "状态枚举")
@AllArgsConstructor
@Getter
public enum StatusEnum {
    CREATING("正在创建","CREATING",0),
    CREATED("已创建", "CREATED",1),

    INITIALIZED("已初始化", "INITIALIZED",2),
    NORMAL("正常", "NORMAL",3),
    WORKING("正在执行","WORKING", 4),
    WAITING("等待中","WAITING", 5),
    OPENED("已开放", "OPENED",6),
    COMPLETED_HALFWAY("在中途已完成","COMPLETED-HALFWAY", 7),
    DISABLED("已禁用","DISABLED",8),
    ENABLED("已启用","ENABLED",9),
    REJECTED("入职被拒", "REJECTED",10),
    RESTING("休息中", "RESTING", 11),
    REVIEWING("审核中", "REVIEWING", 12),
    NOT_FOUND("已丢失", "NOT_FOUND", 13),
    RESIGNATED("已离职", "RESIGNATED", 14),
    HALF("半开","HALF",15),
    HIDDEN("隐藏", "HIDDEN", 16),

    EXPIRED("已过期", "EXPIRED",30),
    CLOSED("已关闭", "CLOSED",31),
    DIFFERENCE("差异", "DIFFERENCE", 32),
    EXCEPTION("异常", "EXCEPTION",33),
    DELETED("已删除","DELETED",34),
    CANCELLED("已取消","CANCELLED",35),

    FAILURE("失败", "FAILURE",36),
    LOCKED("已锁定","LOCKED", 37),
    PAID("已支付", "PAID", 39),
    EFFECTIVE("已生效","EFFECTIVE",40),
    INEFFECTIVE("无效", "INEFFECTIVE", 41),
    DRAFTING("草稿", "DRAFTING", 42),

    REFUNDING("退款中", "REFUNDING", 46),
    REFUNDED("已退款","REFUNDED", 47),
    BIDDEN("已接单","BIDDEN", 48),
    OFFER_REVIEWING("入职待审","OFFER_REVIEWING", 50),
    LEVEL_UPDATE_REVIEWING("级别变更待审","LEVEL_UPDATE_REVIEWING", 51),
    TERMINATED("已终止","TERMINATED", 52),
    TRANSFER_CREATED("任务转移", "TRANSFER_CREATED", 57),
    CHANGED("已变更","CHANGED", 58),
    NO_BID_CANCELLING("无人接单取消中","EXPIRED_CANCELLING",59),
    COMMITTED("已提交", "COMMITTED",60),
    ROLLED_BACK("已回滚", "ROLLED_BACK", 61),
    AUTHORIZED("已授权","AUTHORIZED",62),
    DELAYED("已延迟", "DELAYED",63),

    COMPLETED("已完成","COMPLETED",100),

    TRANSFERRED("任务已转移","TRANSFERRED",102),
    FINANCE_REVIEW("财务审核", "FINANCE_REVIEW",105),
    DEFRAY_COST("支付费用", "DEFRAY_COST", 106),
    RESUMED("已消费", "RESUMED", 107),
    PROCESSING("处理中","PROCESSING", 108),
    ACCEPTED("已受理", "ACCEPTED", 109),
    FALLBACKING("回退中", "FALLBACKING", 110),
    FALLBACK_END("回退结束", "FALLBACK_END", 111),
    SETTLING("结算中", "SETTLING", 112),

    SUCCESS("成功","SUCCESS",200),
    SETTLED("已结算","SETTLED", 201);


    /**
     * 名称
     * */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 代码
     * */
    @ApiModelProperty("代码")
    private String code;

    /**
     * byte值
     * */
    @ApiModelProperty("byte值")
    private Integer value;

    public static StatusEnum valueOf(Byte value) {
        for(StatusEnum e : StatusEnum.values()) {
            if(e.getValue().equals(value)) {
                return e;
            }
        }
        return  null;
    }
}
