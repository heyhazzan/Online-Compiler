/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hazzan
 */
public class AppEventsListener implements ServletContextListener, HttpSessionListener {
    ServletContext context;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        log("CREATE: ", hse);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession session = hse.getSession();
        long start = session.getCreationTime();
        long end = session.getLastAccessedTime();
        String counter = (String)session.getAttribute("counter");
        log("DESTRY, Session Duration:"+(end-start)+" ms Counter:"+counter,hse);
    }
    
    protected void log(String msg, HttpSessionEvent hse){
        String id = hse.getSession().getId();
        log("SessionID: "+id+" "+msg);
    }
    
    protected void log(String msg){
        System.out.println("["+getClass().getName()+"] "+msg);
    }
    
}
