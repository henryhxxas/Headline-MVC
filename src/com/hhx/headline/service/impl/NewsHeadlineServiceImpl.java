package com.hhx.headline.service.impl;

import com.hhx.headline.dao.NewsHeadlineDao;
import com.hhx.headline.dao.NewsTypeDao;
import com.hhx.headline.dao.impl.NewsHeadlineDaoImpl;
import com.hhx.headline.dao.impl.NewsTypeDaoImpl;
import com.hhx.headline.pojo.NewsHeadline;
import com.hhx.headline.pojo.NewsType;
import com.hhx.headline.pojo.vo.HeadlineDetailVo;
import com.hhx.headline.pojo.vo.HeadlinePageVo;
import com.hhx.headline.pojo.vo.HeadlineQueryVo;
import com.hhx.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:03
 * @Description: TODO
 * @Version: 1.0
 */
public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao newsHeadlineDao=new NewsHeadlineDaoImpl();

    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        /**pageInfo的map集合包含以下信息：
         * pageData:[
         *  {
         *     	"hid":"1",                     // 新闻id
         *     	"title":"尚硅谷宣布 ... ...",   // 新闻标题
         *     	"type":"1",                    // 新闻所属类别编号
         *     	"pageViews":"40",              // 新闻浏览量
         *     	"pastHours":"3" ,              // 发布时间已过小时数
         *     	"publisher":"1"                // 发布用户ID
         *    }
         * ],
         * pageNum:1,
         * pagesize:1,
         * totalPage:1,
         * totalSize:1
         */
        int pageNum=headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData=newsHeadlineDao.fingPageList(headlineQueryVo);
        int totalSize=newsHeadlineDao.findPageCount(headlineQueryVo);
        int totalPage=totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
        Map pageInfo=new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("pageData",pageData);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(int hid) {
        //修改头条的浏览量+1
        newsHeadlineDao.incrPageViews(hid);
        //查询
        return newsHeadlineDao.findHeadlineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        return newsHeadlineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        return newsHeadlineDao.update(newsHeadline);
    }

    @Override
    public int removeByHid(int hid) {
        return newsHeadlineDao.removeByHid(hid);
    }
}
