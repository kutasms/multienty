package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.UploadedFile;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 存储文件 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UploadFileDTO", description="存储文件DTO对象")
public class UploadedFileDTO extends UploadedFile {
}
