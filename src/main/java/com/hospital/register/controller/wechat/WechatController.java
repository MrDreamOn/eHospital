package com.hospital.register.controller.wechat;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.WechatService;
import com.hospital.register.util.ResponseCode;
import com.hospital.register.vo.WeChatRequestVo;

@Controller
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService  wechatService;
    
    @RequestMapping("/handleRequest")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获得头部信息
        String result = null;
        WeChatRequestVo requestVo = new WeChatRequestVo();
        requestVo.setSignature(request.getParameter("signature"));
        requestVo.setEchostr(request.getParameter("echostr"));
       
        if(StringUtils.hasText(requestVo.getSignature())){
            // 调用微信公众号处理方法
            result = wechatService.handleRequest(request);
            logger.info("微信公众号=请求处理返回数据:{}", result);
            // 返回处理结果
            response.setCharacterEncoding("UTF-8");
        }else{
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE, "不支持的请求类型.");
        }
        if(StringUtils.hasText(result)){
            PrintWriter printWriter = response.getWriter();
            printWriter.print(result);
            response.flushBuffer();
        }
    }
}
