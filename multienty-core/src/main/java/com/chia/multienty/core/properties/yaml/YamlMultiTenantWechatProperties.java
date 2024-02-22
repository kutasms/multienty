package com.chia.multienty.core.properties.yaml;

import lombok.Data;

@Data
public class YamlMultiTenantWechatProperties {
    private YamlMultiTenantWechatProgramProperties miniProgram;
    private YamlMultiTenantWechatPayProperties pay;
    private YamlMultiTenantWechatThirdPartyProperties thirdParty;
}
