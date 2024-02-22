package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 符号枚举
 * */
@Getter
@AllArgsConstructor
public enum SymbolEnum {
    DOUBLE_BACK_SLASH("\\\\","双反斜杠"),
    BACK_SLASH("\\","反斜杠"),
    SLASH("/","斜杠"),
    HYPHEN("-", "连字符"),
    UNDER_LINE("_", "下划线"),
    EMPTY_STRING("","空字符串"),
    DOT(".", "点"),
    COMMA(",","逗号"),
    SPACE(" ","空格"),
    PARENTHESES_FORMAT("(%s)", "可格式化小括号"),
    ANGLE_BRACKETS("<%s>", "可格式化尖括号")
    ;

    private String code;
    private String description;
}
