package com.geek.course.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

public class RequestHeadFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        //fullRequest.headers().set("xjava", "kimmking");
        String header = fullRequest.headers().get("xjava");
        if("kimmking".equals(header)){

        }else{

        }
    }


}
