/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 商家登录帐号类型
 * </p>
 * @author Zheng Jie
 * @date 2020-05-07
 */
@Getter
@AllArgsConstructor
public enum LoginAccountType {

    MAIN_ACCOUNT(1, "主账号"),
    SUB_ACCOUNT(2, "子账号");

    private final Integer value;
    private final String description;

    public static LoginAccountType find(Integer val) {
        for (LoginAccountType dataScopeType : LoginAccountType.values()) {
            if (val.equals(dataScopeType.getValue())) {
                return dataScopeType;
            }
        }
        return null;
    }

}
