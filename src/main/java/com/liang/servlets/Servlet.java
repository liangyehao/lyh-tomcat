package com.liang.servlets;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;

import java.io.IOException;

public interface Servlet {


    void service(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
