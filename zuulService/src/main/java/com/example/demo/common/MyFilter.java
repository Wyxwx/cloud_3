package com.example.demo.common;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: wyx
 * @Date: 2019-04-18 20:42
 * @Description:
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String name = request.getParameter("name");
        if(name == null || name.equals("")){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        System.out.println("name: " + name);
        return null;
    }
}
