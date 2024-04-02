package com.hhx.headline.service.impl;

import com.hhx.headline.dao.NewsUserDao;
import com.hhx.headline.dao.impl.NewsUserDaoImpl;
import com.hhx.headline.pojo.NewsUser;
import com.hhx.headline.service.NewsUserService;
import com.hhx.headline.util.MD5Util;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:01
 * @Description: TODO
 * @Version: 1.0
 */
public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao=new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(int userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        //处理增加数据的业务
        //将明文密码转换成密文密码
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return userDao.insertUser(registUser);
    }
}
