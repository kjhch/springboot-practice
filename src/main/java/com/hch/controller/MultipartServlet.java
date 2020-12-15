package com.hch.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.locks.LockSupport;

/**
 * @author hch
 * @since 2020/8/8
 */
@WebServlet(urlPatterns = "/servlet/file")
public class MultipartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // Enumeration<String> headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()) {
        //     String headerName = headerNames.nextElement();
        //     System.out.println(headerName + ": " + request.getHeader(headerName));
        // }
        // System.out.println();
        // try (
        //         // BufferedReader reader = request.getReader();
        //         ServletInputStream inputStream = request.getInputStream();
        //         BufferedInputStream in = new BufferedInputStream(inputStream);
        // ) {
        //     byte[] buf = new byte[8192];
        //     int count;
        //     StringBuilder stringBuilder = new StringBuilder();
        //     while ((count = in.read(buf)) != -1) {
        //         stringBuilder.append(new String(buf, 0, count));
        //     }
        //     System.out.println(stringBuilder);
        // }
        resp.getWriter().println("finish");
    }
}
