package com.hhx.headline.controller;

import com.hhx.headline.common.Result;
import com.hhx.headline.dao.NewsHeadlineDao;
import com.hhx.headline.pojo.NewsHeadline;
import com.hhx.headline.service.NewsHeadlineService;
import com.hhx.headline.service.impl.NewsHeadlineServiceImpl;
import com.hhx.headline.util.JwtHelper;
import com.hhx.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hhx
 * @Date: 2024/4/1 11:06
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService newsHeadlineService=new NewsHeadlineServiceImpl();

    /**
     * 发布头条的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String token = req.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadline.setPublisher(userId.intValue());

        //将信息存入数据库
        newsHeadlineService.addNewsHeadline(newsHeadline);

        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 修改头条回显业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline headline= newsHeadlineService.findByHid(hid);
        Map data=new HashMap();
        data.put("headline",headline);
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 更新头条的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadlineService.update(newsHeadline);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    /**
     * 删除头条业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        newsHeadlineService.removeByHid(hid);
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
