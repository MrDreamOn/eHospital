package com.hospital.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.hospital.register.util.MyMD5;

public class TestClient {

     public static void main(String[] args) {


        
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("hospital_id", "yzdxfsyyadmin");
           //map.put("hospital_area_id", "X");
            //map.put("auth_code", "281314000788376824");
           //map.put("order_amount", "0.01");
            map.put("biz_id", "20180131993-3");
            //map.put("trade_no", "EH201801250000000009");
            //map.put("cashier_id", "张三,192.168.1.1,01:AB:02:03");
           // map.put("data_src", "prepaySrc");
            //map.put("refund_amount", "0.01");
            //map.put("his_id", "tk0001");
            String newsign = getSign(map, "20ee35ec4160196732489ea47d8e534e");
            System.out.println(newsign);
     }
    
    public static String getSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        System.out.println("字典排序结果值result" + result);
        result = MyMD5.encoderByMd5(result);
        System.out.println("字典排序MD5加密后result" + result);
        return result.toUpperCase();
    }

    
}
