package com.chia.multienty.core.properties.yaml;

import lombok.Data;

@Data
public class YamlMultientyWechatProperties {
    private YamlMultientyWechatOfficialAccountProperties oa;
    private YamlMultientyWechatPayProperties pay;
    private YamlMultientyWechatThirdPartyProperties thirdParty;
    private YamlMultientyWechatPcProperties pc;
    private YamlMultientyWechatMppProperties mpp;
}
