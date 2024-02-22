package com.chia.multienty.core.domain.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Web日志用户接口
 * 自动化录入日志时，将Request中的user转换为IWebLogUser，用以保存日志
 * */
public interface IWebLogUser {
    @JsonIgnore
    Long getUserId();
    @JsonIgnore
    String getUserName();
}
