package com.hospital.register.vo;

import java.util.Map;


public class WeChatRequestVo {
    /**请求方式*/
    private String method;
    /**签名*/
    private String signature;
    /**时间戳*/
    private String timestamp;
    /**随机数*/
    private String nonce;
    /**请求串*/
    private String echostr;
    /**医院ID*/
    private String hospitalId;
    /**消息体*/
    private String message;
    
    private Map<String, String> msgContentMap;
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getNonce() {
        return nonce;
    }
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
    public String getEchostr() {
        return echostr;
    }
    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
    public String getHospitalId() {
        return hospitalId;
    }
    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, String> getMsgContentMap() {
        return msgContentMap;
    }
    public void setMsgContentMap(Map<String, String> msgContentMap) {
        this.msgContentMap = msgContentMap;
    }
    
}
