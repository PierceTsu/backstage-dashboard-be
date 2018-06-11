package com.pierce.data;

import com.pierce.data.utils.StrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : data
 * @Package Name : com.pierce.data
 * @Description: TODO
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-06-05 16:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PwdTest {

    @Value("${salt}")
    private static String salt;

    @Test
    public void encryptPwd() {
//        System.out.println("salt: " + salt);
        System.out.println(StrUtil.encryptedPwd("123456", "test"));
    }
}
