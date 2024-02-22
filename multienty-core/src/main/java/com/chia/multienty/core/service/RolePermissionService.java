package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RolePermissionDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.RolePermission;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 账户角色关联权限实体 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
public interface RolePermissionService extends KutaBaseService<RolePermission> {

    RolePermissionDTO getDetail(RolePermissionDetailGetParameter parameter);

    void delete(RolePermissionDeleteParameter parameter);

    IPage<RolePermissionDTO> getPage(RolePermissionPageGetParameter parameter);

    void save(RolePermissionSaveParameter parameter);

    void update(RolePermissionUpdateParameter parameter);


    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { Exception.class})
    boolean changePermissions(RolePermissionChangeParameter parameter);
}
