package com.hhx.headline.dao;

import com.hhx.headline.pojo.NewsHeadline;
import com.hhx.headline.pojo.vo.HeadlineDetailVo;
import com.hhx.headline.pojo.vo.HeadlinePageVo;
import com.hhx.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

/**
 * @Author: hhx
 * @Date: 2024/4/1 10:57
 * @Description: TODO
 * @Version: 1.0
 */
public interface NewsHeadlineDao {
    /**
     *根据传入信息返回新闻的编号、题目、类型。。。
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> fingPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据传入的信息返回新闻总条数
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    /**
     * 修改头条的浏览量
     * @param hid
     * @return
     */
    int incrPageViews(int hid);

    /**
     * 根据头条的编号查询头条的具体信息
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * 添加新的头条
     * @param newsHeadline
     * @return
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
     * 根据id对头条的is_deleted属性进行修改(即删除)
     * @param hid
     * @return
     */
    int removeByHid(int hid);
}
