package com.chia.multienty.mail.core;

import com.chia.multienty.plugin.core.metadata.AbstractMultientyPlugin;

public abstract class AbstractMultientySMSPlugin extends AbstractMultientyPlugin implements IMultientyMailPlugin{
    @Override
    public String getTypeName() {
        return "MAIL";
    }

    @Override
    public boolean isOverrideNameOnRegister() {
        return false;
    }
}
