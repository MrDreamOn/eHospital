package com.hospital.register.service;

import javax.servlet.http.HttpServletRequest;

public interface WechatService {

    /**
     * 微信公众号/支付宝服务窗入口处理方法
     * 
     * @param request
     * @return
     */
    public String handleRequest(HttpServletRequest request);
    
    
}
