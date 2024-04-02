package com.hhx.headline.dao;

import com.hhx.headline.pojo.NewsUser;

/**
 * @Author: hhx
 * @Date: 2024/4/1 10:56
 * @Description: TODO
 * @Version: 1.0
 */
public interface NewsUserDao {
    /**
     * 根据用户登录的账号查找用户信息的方法
     * @param username 用户输入账户
     * @return 找到了返回NewsUser对象，找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查找用户信息的方法
     *
     * @param userId 用户输入id
     * @return 找到了返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(int userId);

    /**
     * 注册用户
     * @param newsUser 输入的用户信息
     * @return
     */
    Integer insertUser(NewsUser registUser);
}
