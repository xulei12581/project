package com.geek.course.io;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpClientController {

    @GetMapping("/httpClient")
    public String httpClient() throws Exception{
        // TODO Auto-generated method stub
        String str = "";
        //1.创建一个httpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //2.创建uriBuilder 对于httpClient4.3访问指定页面url必须要使用http://开始
        URIBuilder uriBuilder = new URIBuilder("http://127.0.0.1:8808/test");
        //4.创建httpget对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //5.设置请求报文头部的编码
        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; utf-8"));
        //6.设置期望服务返回的编码
        httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        //7.请求服务
        CloseableHttpResponse response = client.execute(httpGet);
        //8.获取请求返回码
        int statusCode = response.getStatusLine().getStatusCode();
        //9.如果请求返回码是200，则说明请求成功
        if (statusCode == 200) {
            //10.获取返回实体
            HttpEntity entity = response.getEntity();
            //11.通过EntityUtils的一个工具类获取返回的内容
            str = EntityUtils.toString(entity);
            //System.out.println("请求成功的返回内容：" + str);

        } else {
            str = "请求失败！";
            //System.out.println("请求失败！");
        }
        response.close();
        client.close();
        return  str;
    }
}
