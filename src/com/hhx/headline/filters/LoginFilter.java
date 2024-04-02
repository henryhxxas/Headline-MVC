package com.hhx.headline.filters;

import com.alibaba.druid.sql.parser.Token;
import com.hhx.headline.common.Result;
import com.hhx.headline.common.ResultCodeEnum;
import com.hhx.headline.util.JwtHelper;
import com.hhx.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: hhx
 * @Date: 2024/4/2 15:11
 * @Description: 登录过滤器
 * @Version: 1.0
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        boolean flag=false;
        if (token!=null&&(!JwtHelper.isExpiration(token))){
            flag=true;
        }
        if(flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            WebUtil.writeJson(response, Result.build(null,ResultCodeEnum.NOTLOGIN));
        }
    }
}
