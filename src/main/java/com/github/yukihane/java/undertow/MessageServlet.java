package com.github.yukihane.java.undertow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = getInitParameter("message");
        resp.getWriter().append("<html><body>Hello, " + msg + "</body></html>").close();
    }
}
