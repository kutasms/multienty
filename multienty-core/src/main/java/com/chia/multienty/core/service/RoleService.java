package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RoleDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.user.RoleListGetParameter;
import com.chia.multienty.core.parameter.user.RoleSaveParameter;
import com.chia.multienty.core.parameter.user.RoleUpdateParameter;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface RoleService extends KutaBaseService<Role> {

    boolean deleteSafely(Long roleId) throws KutaRuntimeException;

    IPage<RoleDTO> getList(RoleListGetParameter parameter);

    int save(RoleSaveParameter parameter);

    int update(RoleUpdateParameter parameter) throws KutaRuntimeException;

    boolean disable(Long roleId, Long version);

    boolean disable(Long roleId);

    boolean enable(Long roleId, Long version);

    boolean enable(Long roleId);
}
