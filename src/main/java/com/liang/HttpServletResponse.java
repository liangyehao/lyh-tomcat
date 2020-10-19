package com.liang;

import java.io.IOException;
import java.io.OutputStream;

public class HttpServletResponse {
    private final OutputStream outputStream;

    public HttpServletResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    //将文本转换为字节流
    public void write(String content) {
        try {
            String httpResponse = "HTTP/1.1 200 OK\n" +      //按照HTTP响应报文的格式写入
                    "Content-Type:text/html\n" +
                    "\r\n" +
                    "<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>" +
                    content +          //将页面内容写入
                    "</body></html>";
            outputStream.write(httpResponse.getBytes());      //将文本转为字节流
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
