package com.liang.socket;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatServer {

    public void start(int port) throws IOException {
        System.out.println("TomcatServer started on ："+port);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            // 封装请求
            HttpServletRequest request = new HttpServletRequest(inputStream);
            HttpServletResponse response = new HttpServletResponse(outputStream);
            // 处理请求
            dispatch(request,response);
            accept.close();
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
    }

    public void start() throws IOException {
        this.start(8080);
    }

}
