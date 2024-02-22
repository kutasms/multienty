package com.chia.multienty.core.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 客户已被禁用通知
 * */
@Data
@Accessors(chain = true)
public class UserStatusChangedNotifyBO {
    private Long userId;
    private String status;
}
