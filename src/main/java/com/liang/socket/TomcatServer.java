package com.liang.socket;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;
import com.liang.servlets.ErrorServlet;
import com.liang.servlets.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TomcatServer {

    private final Map<String,String> servletMapping = new HashMap<>();

    public void start(int port) throws IOException {
        // 初始化配置
        initConfig();
        System.out.println("TomcatServer started on ："+port);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            // 封装请求
            HttpServletRequest request = new HttpServletRequest(inputStream);
            HttpServletResponse response = new HttpServletResponse(outputStream);
            // 分发请求
            dispatch(request,response);
            accept.close();
        }
    }

    private void initConfig() {
        System.out.println("初始化servlet配置...");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(loader.getResourceAsStream("servlet-config.properties"));
        } catch (IOException e) {
            System.out.println("初始化servlet配置失败...");
            e.printStackTrace();
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            System.out.println("["+key+"]");
            this.servletMapping.put(key, value);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getUri();
        try {
            Servlet servlet = getServlet(uri);
            servlet.service(request,response);
        } catch (Exception e) {
            response.write(uri+ " handle failure");
        }
    }

    private Servlet getServlet(String uri) throws Exception {
        if (servletMapping.containsKey(uri)) {
            String className = servletMapping.get(uri);
            Class<?> clazz = Class.forName(className);
            return  (Servlet)clazz.newInstance();
        }
        return new ErrorServlet("404 not found");
    }

    public void start() throws IOException {
        this.start(8080);
    }

}
