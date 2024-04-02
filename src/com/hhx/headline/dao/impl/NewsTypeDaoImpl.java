package com.hhx.headline.dao.impl;

import com.hhx.headline.dao.BaseDao;
import com.hhx.headline.dao.NewsTypeDao;
import com.hhx.headline.pojo.NewsType;

import java.util.List;

/**
 * @Author: hhx
 * @Date: 2024/4/1 10:58
 * @Description: TODO
 * @Version: 1.0
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql="select tid,tname from news_type";
        return baseQuery(NewsType.class,sql);
    }
}
