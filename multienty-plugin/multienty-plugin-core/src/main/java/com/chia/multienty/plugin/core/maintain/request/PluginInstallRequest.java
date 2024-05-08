package com.chia.multienty.plugin.core.maintain.request;

import com.chia.multienty.plugin.core.metadata.PluginSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PluginInstallRequest implements Serializable {
    private String appId;
    private String appSecret;
    private Map<String, Object> attach;
    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    private Long pluginId;

    /**
     * 插件来源
     */
    @ApiModelProperty(value = "插件来源")
    private PluginSource source;
}
