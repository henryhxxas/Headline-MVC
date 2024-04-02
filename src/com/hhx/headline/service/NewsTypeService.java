package com.hhx.headline.service;

import com.hhx.headline.pojo.NewsType;

import java.util.List;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:02
 * @Description: TODO
 * @Version: 1.0
 */
public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型以List<NewsType>集合返回
     */
    List<NewsType> findAll();
}
