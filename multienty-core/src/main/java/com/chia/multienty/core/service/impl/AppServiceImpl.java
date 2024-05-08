package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.AppDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.AppMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.App;
import com.chia.multienty.core.service.AppService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.util.RandomStringUtils;
import com.github.yulichang.toolkit.MPJWrappers;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 应用 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@Service
@RequiredArgsConstructor
public class AppServiceImpl extends KutaBaseServiceImpl<AppMapper, App> implements AppService {


    @Override
    public AppDTO getDetail(AppDetailGetParameter parameter) {
        return selectJoinOne(AppDTO.class,
                        MPJWrappers.<App>lambdaJoin().eq(App::getAppId, parameter.getAppId()));
    }

    @Override
    public void update(AppUpdateParameter parameter) {
        App app = new App();
        BeanUtils.copyProperties(parameter, app);
        updateByIdTE(app);
    }

    @Override
    public void delete(AppDeleteParameter parameter) {
        removeByIdTE(parameter.getAppId());
    }

    @Override
    public IPage<AppDTO> getPage(AppPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), AppDTO.class,
                new MTLambdaWrapper<App>()
                        .solveGenericParameters(parameter)
                        .like(Strings.hasText(parameter.getKeywords()), App::getName, parameter.getKeywords())
                        .in(!ListUtil.isEmpty(parameter.getAppIds()),
                                App::getAppId,
                                parameter.getAppIds())
        );
    }

    @Override
    public void save(AppSaveParameter parameter) {
        App app = new App();
        BeanUtils.copyProperties(parameter, app);
        app.setAppId(IdWorkerProvider.next());
        app.setAppNo(RandomStringUtils.getRandomCode(7, 3));
        saveTE(app);
        parameter.setAppId(app.getAppId());
    }


    @Override
    public void enable(AppEnableParameter parameter) {
        App app = new App();
        BeanUtils.copyProperties(parameter, app);
        app.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(app);

    }

    @Override
    public void disable(AppDisableParameter parameter) {
        App app = new App();
        BeanUtils.copyProperties(parameter, app);
        app.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(app);
    }
}
