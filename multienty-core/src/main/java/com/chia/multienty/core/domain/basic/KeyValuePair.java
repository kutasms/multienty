package com.chia.multienty.core.domain.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class KeyValuePair<K, V> {
    @TableField("`key`")
    private K key;
    @TableField("`value`")
    private V value;
}
