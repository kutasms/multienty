package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileStorageMode {
    LOCAL(1, "本地存储"),
    OSS(2,"阿里云对象存储"),
    OBS(3,"华为云对象存储"),
    COS(4,"腾讯云分布式存储"),
    CUSTOM(5, "自定义存储终端");
    private final Integer value;
    private final String description;

    public static FileStorageMode valueOf(Integer val) {
        for (FileStorageMode mode : FileStorageMode.values()) {
            if (val.equals(mode.getValue())) {
                return mode;
            }
        }
        return null;
    }
}
