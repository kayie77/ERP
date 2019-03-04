package com.xhwl.erp.util.util;

import java.util.UUID;

/**
 * 生成随机码的工具类
 * @author jiayiwu
 * @date 2018年2月26日
 */
public class RandomUtil {
    /**
     * 生成前缀+32位编码 用作id
     *
     * @return string
     */
    public static String genarateId(String prefix) {
        String uuid = prefix + UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}

