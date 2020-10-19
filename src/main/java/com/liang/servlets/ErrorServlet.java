package com.liang.servlets;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;

public class ErrorServlet implements Servlet {

    private final String errMsg;

    public ErrorServlet(String errMsg){
        this.errMsg = errMsg;
    }


    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        response.write(errMsg);
    }
}
