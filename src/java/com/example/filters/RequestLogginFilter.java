/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.filters;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Hazzan
 */
public class RequestLogginFilter implements Filter{
    private ServletContext context;
    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.context = fc.getServletContext();
        this.context.log("Request Loggin Filter initialized.");
    }

    /**
     *
     * @param sr
     * @param sr1
     * @param fc
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)sr;
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            String value = req.getParameter(name);
            this.context.log(req.getRemoteAddr()+"::Request Params::{"+name+"="+value+"}");
        }
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
    }
    
}
