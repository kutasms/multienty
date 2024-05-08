package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 系统通知信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "NoticeUpdateParameter",description = "系统通知信息更新请求")
public class NoticeUpdateParameter {

        /**
         * 消息id
         */
        @LogMetaId
        @ApiModelProperty(value = "消息id")
        private Long noticeId;
        /**
         * 消息类型
         */
        @ApiModelProperty(value = "消息类型")
        private Byte type;
        /**
         * 跳转类型
         */
        @ApiModelProperty(value = "跳转类型")
        private Byte jumpMode;
        /**
         * 接收者类型
         */
        @ApiModelProperty(value = "接收者类型")
        private Byte receiverType;
        /**
         * 接收者id
         */
        @ApiModelProperty(value = "接收者id")
        private Long userId;
        /**
         * 标题
         */
        @ApiModelProperty(value = "标题")
        private String subject;
        /**
         * 消息内容
         */
        @ApiModelProperty(value = "消息内容")
        private String message;
        /**
         * 跳转元标识
         */
        @ApiModelProperty(value = "跳转元标识")
        private Long jumpMetaId;
        /**
         * 图片
         */
        @ApiModelProperty(value = "图片")
        private String mainImage;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        /**
         * 状态
         */
        @ApiModelProperty(value = "状态")
        private String status;
        /**
         * 是否已删除
         */
        @ApiModelProperty(value = "是否已删除")
        private Boolean deleted;
}
