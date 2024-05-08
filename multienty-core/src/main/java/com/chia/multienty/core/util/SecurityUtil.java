package com.chia.multienty.core.util;

import com.chia.multienty.core.domain.vo.LoggedUserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static LoggedUserVO getLoggedUser() {
        return (LoggedUserVO) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        return getLoggedUser().getSuperAdmin();
    }

    public static Long getUserId() {
        return getLoggedUser().getLogUserId();
    }
}
