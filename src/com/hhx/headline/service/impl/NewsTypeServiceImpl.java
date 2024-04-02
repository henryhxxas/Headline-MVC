package com.hhx.headline.service.impl;

import com.hhx.headline.dao.NewsTypeDao;
import com.hhx.headline.dao.impl.NewsTypeDaoImpl;
import com.hhx.headline.pojo.NewsType;
import com.hhx.headline.service.NewsTypeService;

import java.util.List;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:02
 * @Description: TODO
 * @Version: 1.0
 */
public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao typeDao=new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}
