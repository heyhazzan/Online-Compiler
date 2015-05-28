/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hazzan
 */
public class AuthenticationFilter implements Filter{
    private ServletContext context;
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.context = fc.getServletContext();
        this.context.log("Authentication filter initialized.");
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)sr;
        HttpServletResponse res = (HttpServletResponse)sr1;
        
        String uri = req.getRequestURI();
        this.context.log("Request Resource: " + uri);
        HttpSession session = req.getSession(false);
        if (session==null && !(uri.endsWith("html") || uri.endsWith("loginServlet"))){
            this.context.log("Unauthorized access request via Authentiaction code.");
            res.sendRedirect("index.html");
        }else{
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
    }
    
}
