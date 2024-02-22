package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.UploadedFileDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 存储文件分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UploadFilePageGetParameter",description = "存储文件分页列表查询请求")
public class UploadedFilePageGetParameter extends DefaultListGetParameter<UploadedFileDTO> {

    /**
     * 文件编号
     */
     @ApiModelProperty(value = "文件编号列表")
     private List<Long> fileIds;
}
