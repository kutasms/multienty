package com.chia.multienty.core.properties.yaml;

import lombok.Data;

@Data
public class YamlMultientyWechatProperties {
    private YamlMultientyWechatProgramProperties miniProgram;
    private YamlMultientyWechatPayProperties pay;
    private YamlMultientyWechatThirdPartyProperties thirdParty;
}
