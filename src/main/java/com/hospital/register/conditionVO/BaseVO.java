package com.hospital.register.conditionVO;

/**
 * 基础信息
 * 
 * @author caiwei
 * @version $Id: BaseVO.java, v 0.1 2017年12月26日 下午10:34:20 caiwei Exp $
 */
public class BaseVO {

    private String result;//success或者error
    
    private String message;//错误说明

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
