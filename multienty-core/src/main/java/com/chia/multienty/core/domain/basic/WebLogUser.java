package com.chia.multienty.core.domain.basic;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WebLogUser implements IWebLogUser{

    private String userName;
    private Long userId;

    private static WebLogUser instance;

    public static WebLogUser getInstance() {
        if(instance == null) {
            instance = new WebLogUser();
        }
        return instance;
    }
}
