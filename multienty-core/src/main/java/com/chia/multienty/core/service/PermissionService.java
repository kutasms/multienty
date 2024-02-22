package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.domain.vo.permission.PermissionVO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Permission;

import java.util.List;

/**
 * <p>
 * 权限菜单信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface PermissionService extends KutaBaseService<Permission> {

    List<PermissionDTO> getUserPermissions(Long userId, Long owner);

    IPage<PermissionDTO> getPlatformTopPermissions(TopPermissionListGetParameter parameter);

    List<PermissionDTO> getChildren(List<String> pidList);

    IPage<PermissionDTO> getFormattedPlatformTopPermissions(TopPermissionListGetParameter parameter);

    List<PermissionDTO> getFormattedDTO(Long pid, List<PermissionDTO> permissions);

    List<PermissionVO> getFormattedVO(List<PermissionDTO> permissions);

    PermissionDTO getDetail(PermissionDetailGetParameter parameter);

    void delete(PermissionDeleteParameter parameter);

    IPage<PermissionDTO> getPage(PermissionPageGetParameter parameter);


    int save(PermissionSaveParameter parameter);

    int update(PermissionUpdateParameter parameter);

    boolean disable(Long permissionId, Long version);

    boolean disable(Long permissionId);

    boolean enable(Long permissionId, Long version);

    boolean enable(Long permissionId);

    List<PermissionDTO> getFuncPermissions(FuncPermissionListGetParameter parameter);
}
