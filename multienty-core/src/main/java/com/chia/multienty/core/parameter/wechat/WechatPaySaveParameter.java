package com.chia.multienty.core.parameter.wechat;

import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 微信支付保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatPaySaveParameter",description = "微信支付保存请求")
@Accessors(chain = true)
public class WechatPaySaveParameter {

    /**
     * 微信支付编号
     */
    @ApiModelProperty(value = "微信支付编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long wxPayId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String mchId;
    /**
     * 证书路径
     */
    @ApiModelProperty(value = "证书路径")
    private String certPath;
    /**
     * 商户私钥路径
     */
    @ApiModelProperty(value = "商户私钥路径")
    private String privateKeyPath;
    /**
     * 商户证书序列号
     */
    @ApiModelProperty(value = "商户证书序列号")
    private String serialNumber;
    /**
     * v3版本key
     */
    @ApiModelProperty(value = "v3版本key")
    private String apiV3Key;
    /**
     * v2版本key
     */
    @ApiModelProperty(value = "v2版本key")
    private String apiV2Key;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 程序编号
     */
    @ApiModelProperty(value = "程序编号")
    private Long programId;
    /**
     * 私钥文件列表
     */
    @ApiModelProperty(value = "私钥文件列表")
    private List<UploadedFileDTO> privateKeyFiles;
    /**
     * 证书文件列表
     */
    @ApiModelProperty(value = "证书文件列表")
    private List<UploadedFileDTO> certificateFiles;
}
