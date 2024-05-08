package com.chia.multienty.core.util;

import com.chia.multienty.core.tools.PhoneCodeAuthenticationManager;
import com.chia.multienty.core.tools.WechatAuthenticationManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReactiveAuthenticationManagerUtil {

    @SneakyThrows
    public static Optional<ReactiveAuthenticationManager> getUsernamePasswordAuthenticationManager(
            ObjectProvider<Map<String,ReactiveAuthenticationManager>> authenticationManagerMap) {

        Map<String, ReactiveAuthenticationManager> available = authenticationManagerMap.getIfAvailable();
        for (ReactiveAuthenticationManager authenticationManager : available.values()) {
            if(authenticationManager instanceof DelegatingReactiveAuthenticationManager) {

                Field field = authenticationManager.getClass().getDeclaredField("delegates");
                field.setAccessible(true);
                List<ReactiveAuthenticationManager> delegates =
                        (List<ReactiveAuthenticationManager>) field.get(authenticationManager);
                return delegates.stream().filter(p->p instanceof UserDetailsRepositoryReactiveAuthenticationManager)
                        .findAny();
            }
        }
        return null;
    }

    @SneakyThrows
    public static Optional<ReactiveAuthenticationManager> getPhoneCodeAuthenticationManager(
            ObjectProvider<Map<String,ReactiveAuthenticationManager>> authenticationManagerMap) {

        Map<String, ReactiveAuthenticationManager> available = authenticationManagerMap.getIfAvailable();
        for (ReactiveAuthenticationManager authenticationManager : available.values()) {
            if(authenticationManager instanceof DelegatingReactiveAuthenticationManager) {

                Field field = authenticationManager.getClass().getDeclaredField("delegates");
                field.setAccessible(true);
                List<ReactiveAuthenticationManager> delegates =
                        (List<ReactiveAuthenticationManager>) field.get(authenticationManager);
                return delegates.stream().filter(p->p instanceof PhoneCodeAuthenticationManager)
                        .findAny();
            }
        }
        return null;
    }

    @SneakyThrows
    public static Optional<ReactiveAuthenticationManager> getWechatAuthenticationManager(
            ObjectProvider<Map<String,ReactiveAuthenticationManager>> authenticationManagerMap) {

        Map<String, ReactiveAuthenticationManager> available = authenticationManagerMap.getIfAvailable();
        for (ReactiveAuthenticationManager authenticationManager : available.values()) {
            if(authenticationManager instanceof DelegatingReactiveAuthenticationManager) {

                Field field = authenticationManager.getClass().getDeclaredField("delegates");
                field.setAccessible(true);
                List<ReactiveAuthenticationManager> delegates =
                        (List<ReactiveAuthenticationManager>) field.get(authenticationManager);
                return delegates.stream().filter(p->p instanceof WechatAuthenticationManager)
                        .findAny();
            }
        }
        return null;
    }
}
