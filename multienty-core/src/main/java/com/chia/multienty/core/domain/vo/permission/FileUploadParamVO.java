package com.chia.multienty.core.domain.vo.permission;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "FileUploadParamVO",description = "文件上传参数VO对象")
public class FileUploadParamVO {
    private Integer index;
    private String type;

}
