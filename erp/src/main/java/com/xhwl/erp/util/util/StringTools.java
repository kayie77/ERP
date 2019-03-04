package com.xhwl.erp.util.util;

/**
 * string 工具类
 * @author jiayiwu
 * @date 2018年3月20日
 */
public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
