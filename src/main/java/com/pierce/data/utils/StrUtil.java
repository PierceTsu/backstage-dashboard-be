package com.pierce.data.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Project : data
 * @Package Name : com.pierce.data.utils
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-11 10:45
 */
public class StrUtil {

    public static String encryptedPwd(String beforeStr, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleHash md = new SimpleHash("MD5", beforeStr,credentialsSalt,2);
        return md.toString();
    }
}
