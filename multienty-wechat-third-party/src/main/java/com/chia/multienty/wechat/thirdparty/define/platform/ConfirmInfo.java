package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfirmInfo {
    @JsonProperty("need_confirm")
    private Boolean needConfirm;
    @JsonProperty("already_confirm")
    private Boolean alreadyConfirm;
    @JsonProperty("can_confirm")
    private Boolean canConfirm;
}
