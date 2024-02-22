
package com.chia.multienty.core.util;

import javax.validation.constraints.NotNull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 日期工具类
 */
public class TimeUtil {

    public static boolean validDurationArray(LocalDateTime[] duration) {
        return (duration != null && (duration[0] != null || duration[1] != null));
    }

    /**
     * 获取开始日期和结束日期内的每一天，包括开始日期和结束日期
     * @param start 开始日期
     * @param end 结束日期
     * @return
     */
    public static List<LocalDate> everyDayBetween(@NotNull LocalDate start, @NotNull LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        long distance = ChronoUnit.DAYS.between(start, end);
        Stream.iterate(start, d-> d.plusDays(1)).limit(distance + 1).forEach(f->{
            dates.add(f);
        });
        return dates;
    }

    /**
     * 获取闭区间范围内的天数
     * @param start
     * @param end
     * @return
     */
    public static long betweenDaysInClosedInterval(@NotNull LocalDate start, @NotNull LocalDate end) {
        return ChronoUnit.DAYS.between(start, end) + 1;
    }

    /**
     * 计算剩余天数
     * @param start
     * @param end
     * @return
     */
    public static long betweenDays(@NotNull LocalDate start, @NotNull LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 将LocalDateTime转换为时间刻度数（毫秒）
     * @param date
     * @return
     */
    public static long toMills(LocalDateTime date) {
        return date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将毫秒数转换为剩余时间格式的字符串
     * @param mills
     * @return
     */
    public static String toRemainExp(Long mills, boolean showMills) {

        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = mills / dd;
        Long hour = (mills - day * dd) / hh;
        Long minute = (mills - day * dd - hour * hh) / mi;
        Long second = (mills - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = mills - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if(day > 0) {
            sb.append(day+"天");
        }
        if(hour > 0) {
            sb.append(hour+"小时");
        }
        if(minute > 0) {
            sb.append(minute+"分");
        }
        if(second > 0) {
            sb.append(second+"秒");
        }
        if(showMills && milliSecond > 0) {
            sb.append(milliSecond+"毫秒");
        }
        return sb.toString();

    }

    /**
     * 将毫秒级时间刻度数转换为LocalDatetime
     * @param timestamp 时间刻度数
     * @return
     */
    public static LocalDateTime parseTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取剩余毫秒数
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static long getRemainMills(LocalDateTime start, LocalDateTime end) {
        return toMills(end) - toMills(start);
    }

    /**
     * 获取今日零点零分零秒
     * @return
     */
    public static LocalDateTime startOfToday() {
        return LocalDate.now().atStartOfDay();
    }




    /**
     * 获取当前时间的紧密连接形式 14位字符串
     * @return
     */
    public static String to14() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(LocalDateTime.now());
    }



    /**
     * 获取当前时间的19位字符串(yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String to19(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateTime);
    }

    /**
     * 获取当前时间的紧密连接形式 8位字符串(仅包括年月日)
     * @return
     */
    public static String to8(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return formatter.format(dateTime);
    }

    /**
     * 获取yyyyMM格式字符串
     * @param date
     * @return
     */
    public static String to6(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        return formatter.format(date);
    }

    /**
     * 将yyyyMMdd格式的字符串转换为LocalDate
     * @param src
     * @return
     */
    public static LocalDate s8ToDate(String src) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(src, formatter);
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为LocalDate
     * @param src
     * @return
     */
    public static LocalDate s10ToDate(String src) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(src, formatter);
    }
    /**
     * 将yyyyMMddHHmmss格式的字符串转换为LocalDateTime
     * @param src
     * @return
     */
    public static LocalDateTime s14ToDate(String src) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(src, formatter);
    }
    /**
     * 将yyyy-MM-dd'T'HH:mm:ss+08:00格式的字符串转换为LocalDateTime
     * @param src
     * @return
     */
    public static LocalDateTime isoToDateTime(String src) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+08:00");
        return LocalDateTime.parse(src, formatter);
    }
    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为LocalDateTime
     * @param src
     * @return
     */
    public static LocalDateTime s19ToDateTime(String src) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(src, formatter);
    }

    /**
     * 获取当天最晚时间
     * @return
     */
    public static LocalDateTime todayMixTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 获取当天最早时间
     * @return
     */
    public static LocalDateTime todayMaxTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取指定日期的最大时间
     * @param date
     * @return
     */
    public static LocalDateTime maxTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }
    /**
     * 获取指定日期的最小时间
     * @param date
     * @return
     */
    public static LocalDateTime minTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }
}
