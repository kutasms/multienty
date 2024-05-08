package com.chia.multienty.wechat.thirdparty.parameter.code;

import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditItem;
import lombok.Data;

import java.util.List;

@Data
public class AuditSubmitParameter {
    private String feedbackInfo;
    private List<CodeAuditItem> itemList;
}
