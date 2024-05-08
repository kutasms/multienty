package com.chia.multienty.wechat.thirdparty.parameter.domain;

import lombok.Data;

import java.util.List;

@Data
public class JumpDomainModifyDirectlyParameter {
    private String action;
    private List<String> webViewDomains;
}
