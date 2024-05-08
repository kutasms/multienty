package com.chia.multienty.plugin.core.maintain.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PluginInstallResponse implements Serializable {
    private Boolean completed;
    public static PluginInstallResponse ok() {
        return new PluginInstallResponse().setCompleted(true);
    }

    public static PluginInstallResponse fail() {
        return new PluginInstallResponse().setCompleted(false);
    }
}
