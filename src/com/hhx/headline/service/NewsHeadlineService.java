package com.hhx.headline.service;

import com.hhx.headline.pojo.NewsHeadline;
import com.hhx.headline.pojo.NewsType;
import com.hhx.headline.pojo.vo.HeadlineDetailVo;
import com.hhx.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:03
 * @Description: TODO
 * @Version: 1.0
 */
public interface NewsHeadlineService {

    /**
     * 根据条件搜索分页信息,返回含页码数,页大小,总页数,总记录数,当前页数据等信息,并根据时间降序,浏览量降序排序
     * @param headlineQueryVo
     * @return
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据头条编号返回具体的头条信息
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * 添加新的头条
     * @param newsHeadline
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据新闻id查询新闻的完整信息并响应给前端
     * @param hid
     * @return
     */
    NewsHeadline findByHid(int hid);

    /**
     * 更新业务头条
     * @param newsHeadline
     */
    int update(NewsHeadline newsHeadline);

    /**
     * 根据id删除业务头条
     * @param hid
     * @return
     */
    int removeByHid(int hid);
}
