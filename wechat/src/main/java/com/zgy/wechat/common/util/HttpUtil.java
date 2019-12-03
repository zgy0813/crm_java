package com.zgy.wechat.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * http
 */
@Slf4j
public class HttpUtil {


    /**
     * Get方法
     */
    public static String httpGet(String server, String params) {
        String response = "";
        try {
            URL url = new URL(server + "?" + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置该连接是可以输出的
            connection.setDoOutput(true);

            // 设置请求方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            response = httpResponse(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Post方法发送form表单
     */
    public static void httpPost(String server, Map<String, Object> params) {
        try {
            URL url = new URL(server);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            ObjectMapper objectMapper = new ObjectMapper();
            pw.write(objectMapper.writeValueAsString(params));

//            pw.write(params);
            pw.flush();
            pw.close();

            String response = httpResponse(connection);
            connection.disconnect();

            // TODO

//            logger.info("response = {}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取返回数据
     */
    private static String httpResponse(HttpURLConnection connection) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder result = new StringBuilder();

            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line + "\n");
            }

            return result.toString();
        }catch (IOException e) {
            log.warn("读取http返回失败.");

            return null;
        }
    }
}
