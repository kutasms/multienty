package com.chia.multienty.plugin.core.tools;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PluginScopeResolver {
    public Duration resolve(String scope) {
        String regex = "^(\\d+)([a-zA-Z])$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(scope);
        if(matcher.find()) {
            String number = matcher.group(1);
            String unit = matcher.group(2);
            ChronoUnit chronoUnit = null;
            switch (unit) {
                case "y":
                    chronoUnit = ChronoUnit.YEARS;
                    break;
                case "M":
                    chronoUnit = ChronoUnit.MONTHS;
                    break;
                case "d":
                    chronoUnit = ChronoUnit.DAYS;
                    break;
                case "H":
                    chronoUnit = ChronoUnit.HOURS;
                    break;
                case "m":
                    chronoUnit = ChronoUnit.MINUTES;
                    break;
                case "s":
                    chronoUnit = ChronoUnit.SECONDS;
                    break;
                case "f":
                    chronoUnit = ChronoUnit.MILLIS;
                    break;
                default:
                    throw new KutaRuntimeException(HttpResultEnum.ARG_ERROR_PATTERN, "scope->unit");
            }
            return Duration.of(Long.parseLong(number), chronoUnit);
        }
        throw new KutaRuntimeException(HttpResultEnum.ARG_ERROR_PATTERN, "scope");
    }
}
