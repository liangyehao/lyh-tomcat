package com.liang;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {

    private String method;

    private String url;

    private String uri;

    private String protocol;

    private Map<String, String> headers;

    private Map<String,String> parameterMap;

    public HttpServletRequest(InputStream inputStream) throws IOException {
        resolveRequest(inputStream);
    }

    /**
     * 解析请求
     *
     * @param inputStream 输入流
     */
    private void resolveRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] bytes = new byte[1024];
        int l;
        if ((l = inputStream.read(bytes))>0){
            httpRequest = new String(bytes,0,l);
        }
        String[] split = httpRequest.split("\n");
        handleFirstLine(split[0]);
        handleHeaders(split);
        System.out.println(httpRequest);
    }

    private void handleHeaders(String[] split) {
        String headerSplit = ": ";
        Map<String, String> headers = new HashMap<>();
        for (String line : split) {
            if (line.contains(headerSplit)) {
                String[] strings = line.split(headerSplit);
                headers.put(strings[0].trim(),strings[1].trim());
            }
        }
        // headers
        setHeaders(headers);
    }

    /**
     * 处理第一行
     * method url uri protocol parameterMap
     *
     * @param firstLine 第一行
     */
    private void handleFirstLine(String firstLine) {
        String[] strings = firstLine.split(" ");
        // method
        setMethod(strings[0]);
        // protocol
        setProtocol(strings[2]);
        // url
        setUrl(strings[1]);
        String[] split = strings[1].split("\\?");
        // uri
        setUri(split[0]);
        // parameterMap
        Map<String, String> parameterMap = new HashMap<>();
        String params = split[1];
        String[] paramArray = params.split("&");
        for (String param : paramArray) {
            String[] kv = param.split("=");
            String key = kv[0];
            String value = kv[1];
            parameterMap.put(key, value);
        }
        setParameterMap(parameterMap);
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
