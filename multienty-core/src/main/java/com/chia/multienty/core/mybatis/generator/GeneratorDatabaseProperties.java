package com.chia.multienty.core.mybatis.generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratorDatabaseProperties {
    private String host;
    private Integer port;
    private String dbName;
    private String username;
    private String password;
}
