package com.liang.servlets;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;

public class IndexServlet implements Servlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        response.write("welcome to my index");
    }
}
