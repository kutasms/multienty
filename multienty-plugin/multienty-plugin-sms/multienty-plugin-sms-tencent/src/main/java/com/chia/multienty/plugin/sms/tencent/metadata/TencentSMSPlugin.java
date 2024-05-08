package com.chia.multienty.plugin.sms.tencent.metadata;

import com.aliyuncs.exceptions.ClientException;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.strategy.sms.domain.GenericContent;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.mail.core.AbstractMultientySMSPlugin;
import com.chia.multienty.plugin.core.maintain.request.PluginInstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUninstallRequest;
import com.chia.multienty.plugin.core.maintain.request.PluginUpgradeRequest;
import com.chia.multienty.plugin.core.maintain.response.PluginInstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUninstallResponse;
import com.chia.multienty.plugin.core.maintain.response.PluginUpgradeResponse;

import java.util.HashMap;
import java.util.Map;

public class TencentSMSPlugin extends AbstractMultientySMSPlugin implements SMSService {

    private final static String PLUGIN_NAME = "TENCENT";
    private final static String PROPERTY_PREFIX = "spring.multienty.plugin.sms";

    @Override
    public String getName() {
        return MultientyConstants.SMS_SERVICE_BEAN_PREFIX + PLUGIN_NAME;
    }


    @Override
    public Map<String, String> getRequiredProperties() {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    @Override
    public PluginInstallResponse install(PluginInstallRequest request) {
        return null;
    }

    @Override
    public PluginUninstallResponse uninstall(PluginUninstallRequest request) {
        return null;
    }

    @Override
    public PluginUpgradeResponse upgrade(PluginUpgradeRequest request) {
        return null;
    }

    @Override
    public String getType() {
        return PLUGIN_NAME;
    }

    @Override
    public SMSResult send(GenericContent content) {
        return null;
    }

    @Override
    public SMSResult sendVerificationCode(VerificationCodeBO verificationCodeBO) throws ClientException {
        return null;
    }
}
