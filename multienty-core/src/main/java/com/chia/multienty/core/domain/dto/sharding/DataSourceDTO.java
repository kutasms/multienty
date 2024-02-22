package com.chia.multienty.core.domain.dto.sharding;

import lombok.Data;

@Data
public class DataSourceDTO {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
