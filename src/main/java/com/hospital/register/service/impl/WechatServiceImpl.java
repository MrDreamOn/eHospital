package com.hospital.register.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.ThresholdingOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.PatientService;
import com.hospital.register.service.WechatService;
import com.hospital.register.util.AddSHA1;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.ResponseCode;
import com.hospital.register.util.WechatUtils;
import com.hospital.register.vo.WeChatRequestVo;

@Service
public class WechatServiceImpl implements WechatService {

    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private PatientService  patientService;
    
    /**
     * 处理微信异步请求
     * @see com.hospital.register.service.WechatService#handleRequest(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public String handleRequest(HttpServletRequest request) {
        try {
            //获取微信异步通知消息内容
            WeChatRequestVo requestVo = getRequestInfo(request);
            String timestamp = requestVo.getTimestamp();
            String signature = requestVo.getSignature();
            String nonce = requestVo.getNonce();
            // 验证签名
            Boolean checkSignResult = true;
            checkSignResult = AddSHA1.checkSignature(signature, timestamp, nonce);

            if (checkSignResult) {
                // 微信接入验证
                if ("GET".equals(requestVo.getMethod())) {
                    return requestVo.getEchostr();
                } else if ("POST".equals(requestVo.getMethod())) {
                    request.setCharacterEncoding("UTF-8");
                    // 解析请求数据
                    Map<String, String> map = requestVo.getMsgContentMap();
                    String openid = map.get("FromUserName");
                    boolean isSub = patientService.isSubscribe(openid);
                    String result = null;
                    // 事件消息
                    if ("event".equals(map.get("MsgType"))) {
                        // 关注事件
                        if ("subscribe".equals(map.get("Event"))) {
                            //查询当前关注的用户是否已经存在.不存在就新建，存在则更新数据库
                            if (isSub) {
                                boolean isReg = patientService.isRegister(openid);
                                if(!isReg){
                                    /**
                                     * 发送图文消息
                                     */
                                    return  responseNewsMessage(map);
                                }
                            } else {
                                //新增微信关注用户
                                patientService.addFollowers(openid);
                            }
                        }

                    }

                } else {
                    throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                        "不支持的微信请求方式.");
                }
            } else {
                throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                    "签名认证失败.");
            }
        } catch (Exception e) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "处理微信异步消息异常.", e);
        }
        return null;
    }

    /**
     * 获取微信请求信息
     * 
     * @param request
     * @return
     */
    private WeChatRequestVo getRequestInfo(HttpServletRequest request) {
        WeChatRequestVo requestVo = new WeChatRequestVo();
        // 请求方式
        requestVo.setMethod(request.getMethod());
        // 微信加密签名
        requestVo.setSignature(request.getParameter("signature"));
        // 时间戳
        requestVo.setTimestamp(request.getParameter("timestamp"));
        // 随机数
        requestVo.setNonce(request.getParameter("nonce"));
        // 随机字符串
        requestVo.setEchostr(request.getParameter("echostr"));
        //医院ID
        String hospitalId = request.getParameter("hospital_id");
        requestVo.setHospitalId(hospitalId);
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return requestVo;
        }
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            // 读取微信发送过来的数据
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
           
            String message = sb.toString();

            message = WechatUtils.decryptByWechat(message, null);

            logger.debug("微信接收信息解密后：" + message);
            //消息体
            requestVo.setMessage(message);

            logger.info("开始解析微信请求数据~~");
            Map<String, String> map = new HashMap<String, String>();
            // 解析xml
            Document doc = DocumentHelper.parseText(requestVo.getMessage());
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element e : list) {
                logger.info("解析微信请求报文：" + e.getName() + "====>" + e.getText());
                map.put(e.getName(), e.getText());
            }
            requestVo.setMsgContentMap(map);
        } catch (Exception e) {
            logger.error("解析微信消息出现异常.", e);
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "解析微信消息出现异常", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        logger.info("微信公众号请求处理完成");
        return requestVo;
    }
    
    
   private static String responseNewsMessage(Map<String,String> map){
       Document doc = DocumentHelper.createDocument();
       Element root = doc.addElement("xml");
       root.addElement("ToUserName").addCDATA(map.get("FromUserName"));
       root.addElement("FromUserName").addCDATA(map.get("ToUserName"));
       root.addElement("CreateTime").addCDATA(DateUtil.getCurrentDate());
       root.addElement("MsgType").addCDATA("news");
       root.addElement("ArticleCount").addCDATA("2");
       Element artElement = root.addElement("Articles");
       Element itemElement = artElement.addElement("item");
       itemElement.addElement("Title").addCDATA("欢迎关注XX医院微信公众号");
       itemElement.addElement("Description").addCDATA("点击立即注册");
       itemElement.addElement("PicUrl").addCDATA("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512845011827&di=6cd456f77c595eb5fc42fd28ecb62d4b&imgtype=0&src=http%3A%2F%2Fnpimg.39.net%2FPictureLib%2FA%2Ff76%2F20150122%2Forg_380277.jpg");
       itemElement.addElement("Url").addCDATA("www.badu.com");
       
       
       logger.info(String.format("需要返回给微信的message数据是:data={}", doc.getRootElement().asXML().trim()));
       return doc.getRootElement().asXML();
   }

   public static void main(String[] args) {
       Map<String,String> map = new HashMap<String, String>();
       map.put("FromUserName", "caiwei");
       map.put("ToUserName", "wei");
    System.out.println(WechatServiceImpl.responseNewsMessage(map));
}
}
