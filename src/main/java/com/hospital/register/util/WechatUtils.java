package com.hospital.register.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hospital.register.vo.PublicNoConfigVo;
import com.hospital.register.vo.WeChatRequestVo;

/**
 * 微信工具类
 * 
 * @author wangliu
 *
 */
public class WechatUtils {

	private static Logger logger = Logger.getLogger(WechatUtils.class);
	
	/**消息格式*/
    private static final String FORMAT = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";

	/**
     * 微信消息加密
     * 
     * @param String
     * @return
     */
    public static String encryptByWechat(String replyMsg, WeChatRequestVo requestVo, PublicNoConfigVo config) {
        if(StringUtils.isEmpty(replyMsg)){
            return replyMsg;
        }
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(PublicNoConfigVo.TOKEN, PublicNoConfigVo.AESKEY, PublicNoConfigVo.APPID);
            String result = pc.encryptMsg(replyMsg, requestVo.getTimestamp(), requestVo.getNonce());
            return result;
        } catch (Exception e) {
            logger.error("加密微信消息出错", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 微信消息解密
     * 
     * @param String
     * @return
     */
    public static String decryptByWechat(String data, PublicNoConfigVo config) {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(PublicNoConfigVo.TOKEN, PublicNoConfigVo.AESKEY, PublicNoConfigVo.APPID);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            StringReader sr = new StringReader(data);
            InputSource is = new InputSource(sr);

            org.w3c.dom.Document document = db.parse(is);
            org.w3c.dom.Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");

            String encrypt = nodelist1.item(0).getTextContent();
            String fromXML = String.format(FORMAT, encrypt);

            String result = pc.decryptMsg(fromXML);
            return result;
        } catch (Exception e) {
            logger.error("解密微信消息出错", e);
            throw new RuntimeException(e);
        }
    }
}
