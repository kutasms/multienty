package com.chia.multienty.core.domain.basic;

import com.chia.multienty.core.pojo.UploadedFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
@ApiModel("上传返回对象")
public class UploadResult {

    @ApiModelProperty("url")
    private String url;

    private UploadedFile uploadedFile;

    @ApiModelProperty("mediaId")
    private String mediaId;

    @ApiModelProperty("透传数据")
    private Map<String, String[]> params;
}
