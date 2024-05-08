package com.chia.multienty.wechat.thirdparty.parameter.code;

import lombok.Data;

@Data
public class CommitParameter {
    private Long templateId;
    private Long programId;
    private String userVersion;
    private String userDesc;
}
