package com.pierce.data.utils;

import java.text.DecimalFormat;

/**
 * @Project : data
 * @Package Name : com.pierce.data.utils
 * @Description: 数字工具类
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-08-08
 */
public class DigitUtil {

    // 默认保留四位小数
    public static final String DEFAULT_PATTERN = "#.####";

    /**
     * 格式化float, 默认4位小数
     * @param number
     * @return
     */
    public static float formatFloat(float number) {
        DecimalFormat df = new DecimalFormat(DEFAULT_PATTERN);
        return Float.valueOf(df.format(number));
    }
}
