package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Notice;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 系统通知信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="NoticeDTO", description="系统通知信息DTO对象")
public class NoticeDTO extends Notice {
}
