package com.hhx.headline.controller;

import com.alibaba.druid.sql.parser.Token;
import com.hhx.headline.common.Result;
import com.hhx.headline.common.ResultCodeEnum;
import com.hhx.headline.dao.impl.NewsUserDaoImpl;
import com.hhx.headline.pojo.NewsUser;
import com.hhx.headline.service.NewsUserService;
import com.hhx.headline.service.impl.NewsUserServiceImpl;
import com.hhx.headline.util.JwtHelper;
import com.hhx.headline.util.MD5Util;
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
 * @Date: 2024/4/1 11:07
 * @Description: NewsUser类的控制层
 * @Version: 1.0
 */
@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    private NewsUserService newsUserService=new NewsUserServiceImpl();
    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名和密码
        /*
        {
        "username":"zhangsan", //用户名
        "userPwd":"123456"     //明文密码
        }
         */
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        //调用服务层方法 实现登录
        NewsUser loginUser=newsUserService.findByUsername(paramUser.getUsername());
        Result result=null;
        if(loginUser!=null){
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                //Integer uid = loginUser.getUid();
                //String token = JwtHelper.createToken(uid.longValue());
                Map data=new HashMap();
                data.put("token",JwtHelper.createToken(loginUser.getUid().longValue()));
                result=Result.ok(data);
            }else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        }else{
            result=Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //向客户端响应登录验证信息
        WebUtil.writeJson(resp,result);
    }


    /**
     * 根据用户token口令获得用户信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的token
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        //校验token
        if(token!=null&&(!token.equals(""))){
            if (!JwtHelper.isExpiration(token)) {
                int userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser=newsUserService.findByUid(userId);
                if(newsUser!=null){
                    //通过校验 查询用户信息放入Result中
                    Map data=new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                    result=Result.ok(data);
                }
            }
        }
        WebUtil.writeJson(resp,result);
    }


    /**
     * 校验用户名是否被占用业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取账号
        String username = req.getParameter("username");
        //根据用户名查询用户信息、找到了返回505 找不到 200
        NewsUser newsUser = newsUserService.findByUsername(username);
        Result result=Result.ok(null);
        if(newsUser!=null){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 完成注册的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收JSON信息
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        //调用服务层将用户信息传入数据
        Integer rows=newsUserService.registUser(newsUser);
        //根据存入是否成功处理响应值
        Result result=Result.ok(null);
        if(rows==0){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * 前端自己校验是否失去灯笼裤状态的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result=Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(token!=null){
            if(!JwtHelper.isExpiration(token)){
                result=Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }
}
