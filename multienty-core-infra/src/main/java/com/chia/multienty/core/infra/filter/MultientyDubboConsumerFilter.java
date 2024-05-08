package com.chia.multienty.core.infra.filter;

import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.tools.MultientyContext;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = CommonConstants.CONSUMER)
public class MultientyDubboConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 在请求前附带http header
        if (MultientyContext.getMppAppId() != null) {
            invocation.setAttachment(MultientyHeaderConstants.MPP_APP_ID_KEY, MultientyContext.getMppAppId().toString());
        }
        invocation.setAttachment(MultientyHeaderConstants.TOKEN_KEY, MultientyContext.getToken());
        if(MultientyContext.getAppType() != null) {
            invocation.setAttachment(MultientyHeaderConstants.APP_ID_KEY, MultientyContext.getAppType().getValue().toString());
        }
        if (MultientyContext.getProgramId() != null) {
            invocation.setAttachment(MultientyHeaderConstants.PROGRAM_ID_KEY, MultientyContext.getProgramId().toString());
        }
        if (MultientyContext.getTenant() != null) {
            invocation.setAttachment(MultientyHeaderConstants.TENANT_ID_KEY, MultientyContext.getTenant().getTenantId().toString());
        }
        Result result = invoker.invoke(invocation);
        return result;
    }

}
