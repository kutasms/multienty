package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.UserRoleDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.UserRole;

import java.util.List;

/**
 * <p>
 * 账户关联角色信息表 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface UserRoleService extends KutaBaseService<UserRole> {

    UserRoleDTO getDetail(UserRoleDetailGetParameter parameter);

    void delete(UserRoleDeleteParameter parameter);

    IPage<UserRoleDTO> getPage(UserRolePageGetParameter parameter);

    void save(UserRoleSaveParameter parameter);

    void update(UserRoleUpdateParameter parameter);


    List<String> getRoleNames(Long userId);

    String getRoleAlias(Long userId);

    List<Role> getRoles(Long userId);

    boolean change(Long userId, Long roleId);

    boolean setRoles(Long userId, List<Long> roleIds);
}
