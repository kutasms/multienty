package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Word;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 关键词信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="WordDTO", description="关键词信息DTO对象")
public class WordDTO extends Word {
}
