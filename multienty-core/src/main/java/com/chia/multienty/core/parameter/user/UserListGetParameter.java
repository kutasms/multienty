package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserListGetParameter",description = "用户列表获取请求")
public class UserListGetParameter extends DefaultListGetParameter<UserDTO> {
}
