package com.hhx.headline.test;

import com.hhx.headline.util.JwtHelper;
import org.junit.Test;

/**
 * @Author: hhx
 * @Date: 2024/4/1 15:38
 * @Description: TODO
 * @Version: 1.0
 */
public class TestJwtHelper {
    @Test
    public void testAllMethod() throws InterruptedException {
        String token = JwtHelper.createToken(1L);
        System.out.println(token);
        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);
        System.out.println(JwtHelper.isExpiration(token));
        Thread.sleep(6000);
        System.out.println(JwtHelper.isExpiration(token));
    }
}
