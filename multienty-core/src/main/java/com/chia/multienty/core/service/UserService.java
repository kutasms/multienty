package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.User;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;

/**
 * <p>
 * 管理账户信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface UserService extends KutaBaseService<User> {

    UserDTO getByToken(String token);

    void refreshToken(String token);

    void saveToken(String token, UserDTO user);

    PublicKeyDTO getPublicKey();

    String createToken(String username, UserDTO user);


    SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter);

    LoginResult login(LoginParameter parameter) throws Exception;

    void logout(LogoutParameter parameter);

    UserDTO getDetail(Long userId);


    LoggedUserVO getUserInfo() throws KutaRuntimeException;

    UserDTO getByPhone(String phone, SFunction<User, ?>... columns);

    IPage<UserDTO> getList(UserListGetParameter parameter);

    int update(UserUpdateParameter parameter);

    int save(UserSaveParameter parameter);

    int deleteSafely(Long platformUserId) throws KutaRuntimeException;

    boolean disable(Long userId, Long version);

    boolean disable(Long userId);

    boolean enable(Long userId, Long version);

    boolean enable(Long userId);
}
