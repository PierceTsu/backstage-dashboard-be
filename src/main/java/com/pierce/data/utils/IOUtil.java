package com.pierce.data.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Project : data
 * @Package Name : com.pierce.data.utils
 * @Description: IO工具类
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-08 18:16
 */
@Slf4j
public class IOUtil {

    public static void closeQuietly(Closeable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
