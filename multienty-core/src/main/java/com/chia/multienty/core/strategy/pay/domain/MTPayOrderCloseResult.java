package com.chia.multienty.core.strategy.pay.domain;

import com.chia.multienty.core.domain.enums.StatusEnum;
import lombok.Data;

@Data
public class MTPayOrderCloseResult {

    /**
     * 返回状态码
     * <p>SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断</p>
     */
    private String returnCode;
    /**
     * 返回信息
     * <p>当return_code为FAIL时返回信息为错误原因 ，例如
     * 签名失败/参数格式校验错误</p>
     */
    private String returnMsg;

    /**
     * 公众账号ID,微信分配的公众账号ID
     */
    private String appid;
    /**
     * 商户号,微信支付分配的商户号
     */
    private String mchId;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 业务结果
     * <p>SUCCESS/FAIL</p>
     */
    private String resultCode;
    /**
     * 业务结果描述
     */
    private String resultMsg;
    /**
     * 错误代码
     * <p>当result_code为FAIL时返回错误代码，详细参见下文错误列表</p>
     */
    private String errCode;
    /**
     * 错误代码描述
     * <p>当result_code为FAIL时返回错误描述，详细参见下文错误列表</p>
     */
    private String errCodeDes;


    /**
     * 交易是否成功
     * @return
     */
    public boolean getSuccess() {
        return StatusEnum.SUCCESS.name().equals(this.resultCode)
                && StatusEnum.SUCCESS.name().equals(this.returnCode);
    }

}
