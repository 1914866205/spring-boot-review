package com.soft1851.springboot.quartz.util;

import java.util.List;

/**
 * @Description 自定义枚举单例对象 StringUtil
 * @Author 涛涛
 * @Date 2020/5/19 10:17
 * @Version 1.0
 **/
public enum StringUtil {
    //枚举类，这里要用枚举的类型
    ;

    /**
     * 获取List参数值
     * @param list
     * @return
     */
    public static String getListString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
