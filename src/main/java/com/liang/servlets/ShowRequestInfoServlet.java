package com.liang.servlets;

import com.liang.HttpServletRequest;
import com.liang.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ShowRequestInfoServlet implements Servlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String stringBuffer = "请求方式：" + request.getMethod() +
                "<br />" +
                "请求协议：" + request.getProtocol() +
                "<br />" +
                "uri：" + request.getUri() +
                "<br />" +
                "url：" + request.getUrl() +
                "<br />" +
                "请求头：<br />" + showHeaders(request.getHeaders());
        response.write(stringBuffer);
    }

    private String showHeaders(Map<String, String> headers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key).append("：").append(value).append("<br />");
        }
        return stringBuilder.toString();
    }
}
