package com.chia.multienty.core.tools;

import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;

public class MultiTenantContext {
    private static final ThreadLocal<TenantDTO> cachedTenant = new ThreadLocal<>();
    private static final ThreadLocal<TenantResourceMappingAlgorithm> cachedResourceMappingAlgorithm = new ThreadLocal<>();
    private static final ThreadLocal<ApplicationType> cachedAppType = new ThreadLocal<>();
    private static final ThreadLocal<String> cachedToken = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> cachedTestAcc = new ThreadLocal<>();

    private static final ThreadLocal<String> cachedMppAppId = new ThreadLocal<>();


    private static ThreadLocal<UserDTO> cachedUser = new ThreadLocal<>();

    public static void setUser(UserDTO user) {
        cachedUser.set(user);
    }

    public static UserDTO getUser() {
        return cachedUser.get();
    }

    public static void clearUser() {
        cachedUser.remove();
    }

    /**
     * 设置小程序编号
     * @param mppAppId
     */
    public static void setMppAppId(String mppAppId) {
        cachedMppAppId.set(mppAppId);
    }

    /**
     * 获取小程序编号
     * @return
     */
    public static String getMppAppId() {
        return cachedMppAppId.get();
    }

    public static void setTestAcc(Boolean testAcc) {
        cachedTestAcc.set(testAcc);
    }
    public static Boolean isTestAcc() {
        return cachedTestAcc.get() != null && cachedTestAcc.get();
    }
    public static void clearTestAcc() {
        cachedTestAcc.remove();
    }
    public static void setAppType(ApplicationType type) {
        cachedAppType.set(type);
    }
    public static ApplicationType getAppType() {
        return cachedAppType.get();
    }
    public static void clearAppType() {
        cachedAppType.remove();
    }

    public static void setToken(String token) {
        cachedToken.set(token);
    }

    public static String getToken() {
        return cachedToken.get();
    }
    public static void clearToken() {
        cachedToken.remove();
    }

    public static void setTenant(TenantDTO tenant) {
        cachedTenant.set(tenant);
    }

    public static TenantDTO getTenant() {
        return cachedTenant.get();
    }
    public static void clearTenant() {
        cachedTenant.remove();
    }

    public static void setResourceMappingAlgorithm(TenantResourceMappingAlgorithm algorithm) {
        cachedResourceMappingAlgorithm.set(algorithm);
    }

    public static TenantResourceMappingAlgorithm getResourceMappingAlgorithm() {
        return cachedResourceMappingAlgorithm.get();
    }
}
