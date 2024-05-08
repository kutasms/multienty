package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.parameter.user.LoginParameter;
import com.chia.multienty.core.parameter.user.LoginVerificationCodeSendParameter;
import com.chia.multienty.core.parameter.user.LogoutParameter;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * <p>
 * 租户信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface TenantService extends KutaBaseService<Tenant>, MultientyUserService {

    TenantDTO getByToken(String token);

    void refreshToken(String token);


    void saveToken(String token, Long tenantId);

    PublicKeyDTO getPublicKey();

    String createToken(TenantDTO tenant);

    Mono<Result<LoginResult>> login(LoginParameter parameter) throws Exception;

    TenantDTO getByPhone(String phone, SFunction<Tenant, ?>... columns);

    @SneakyThrows
    SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter);

    void logout(LogoutParameter parameter);

    TenantDTO getDetail(TenantDetailGetParameter parameter);

    IPage<TenantDTO> getPage(TenantPageGetParameter parameter);

    void save(TenantSaveParameter parameter);

    void update(TenantUpdateParameter parameter);

    void delete(TenantDeleteParameter parameter);

    LoggedUserVO getLoggedInfo() throws KutaRuntimeException, IOException;
}
