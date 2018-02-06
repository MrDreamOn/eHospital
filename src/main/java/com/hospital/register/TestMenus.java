package com.hospital.register;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestMenus {

    public static void main(String[] args) {
        JSONObject m1 = new JSONObject();
        JSONArray  a1 = new JSONArray();
        JSONObject m11 = new JSONObject();
        m11.put("type", "view");
        m11.put("name", "医院介绍");
        m11.put("url", "http://wx.shuyidajiankang.com/login2.html#/hospitalInfo");
        JSONObject m12 = new JSONObject();
        m12.put("type", "view");
        m12.put("name", "医生简介");
        m12.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fef15d232cfb5df&redirect_uri=http%3a%2f%2fwx.shuyidajiankang.com%2flogin2.html%23%2fexpertIntroduction&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        JSONObject m13 = new JSONObject();
        m13.put("type", "view");
        m13.put("name", "来院导航");
        m13.put("url", "http://j.map.baidu.com/BLiuj");
        a1.put(m11);
        a1.put(m12);
        a1.put(m13);
        m1.put("name", "走进健康");
        m1.put("sub_button", a1);
        
        
        JSONObject m2 = new JSONObject();
        JSONArray  a2 = new JSONArray();
        JSONObject m21 = new JSONObject();
        m21.put("type", "view");
        m21.put("name", "在线预约");
        m21.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fef15d232cfb5df&redirect_uri=http%3a%2f%2fwx.shuyidajiankang.com%2flogin2.html%23%2fdoctorVerb&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        JSONObject m22 = new JSONObject();
        m22.put("type", "click");
        m22.put("name", "在线咨询");
        m22.put("key", "V1001");
        JSONObject m23 = new JSONObject();
        m23.put("type", "view");
        m23.put("name", "养生心得");
        m23.put("url", "http://www.cnys.com/xinde/");
        a2.put(m21);
        a2.put(m22);
        a2.put(m23);
        m2.put("name", "名医预约");
        m2.put("sub_button", a2);
        
        
        JSONObject m3 = new JSONObject();
        JSONArray  a3 = new JSONArray();
        JSONObject m31 = new JSONObject();
        m31.put("type", "view");
        m31.put("name", "我的预约");
        m31.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fef15d232cfb5df&redirect_uri=http%3a%2f%2fwx.shuyidajiankang.com%2flogin2.html%23%2fallOrder&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        JSONObject m32 = new JSONObject();
        m32.put("type", "view");
        m32.put("name", "积分查询");
        m32.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fef15d232cfb5df&redirect_uri=http%3a%2f%2fwx.shuyidajiankang.com%2flogin2.html%23%2fscoring&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        JSONObject m33 = new JSONObject();
        m33.put("type", "view");
        m33.put("name", "个人信息");
        m33.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8fef15d232cfb5df&redirect_uri=http%3a%2f%2fwx.shuyidajiankang.com%2flogin2.html%23%2fuserInfo&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        a3.put(m31);
        a3.put(m32);
        a3.put(m33);
        m3.put("name", "个人中心");
        m3.put("sub_button", a3);
        
        
        
        
        JSONObject meuns = new JSONObject();
        JSONArray  a = new JSONArray();
        a.put(m1);
        a.put(m2);
        a.put(m3);
        meuns.put("button", a);
        System.out.println(meuns.toString());
    }

}
