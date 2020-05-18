package com.soft1851.springboot.task.scheduling.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 定时器任务工具类
 * @Author 涛涛
 * @Date 2020/5/18 20:38
 * @Version 1.0
 **/
public class QuartzCronDateUtil {

    /**
     * 转换日期格式
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String timeStr = null;
        if (date != null) {
            timeStr = sdf.format(date);
        }
        return timeStr;
    }

    /**
     * 日期转换cron表达式时间格式
     * @param date
     * @return
     */
    public static String getCron(Date date) {
//        cron表达式的格式
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }
}
