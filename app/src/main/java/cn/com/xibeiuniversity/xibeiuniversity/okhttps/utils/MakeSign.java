package cn.com.xibeiuniversity.xibeiuniversity.okhttps.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * user  秦伟宁
 * Date 2016/3/10
 * Time 10:04
 */
public class MakeSign {
    private static List<String> keyList = null;
    private static String MD5Sign = "";


    public static String makeSignNew(Map<String, String> map)  {

        if(keyList == null){
            keyList = new ArrayList<String>();
        }else {
            keyList.clear();
        }
        for(String key : map.keySet()){
            keyList.add(key);
        }

        Collections.sort(keyList);
        StringBuffer buffer = new StringBuffer();
        for (String key:keyList){
            buffer.append(key).append(map.get(key));
        }
        String MD5Sign = "";
        try {
            MD5Sign = MD5.MD5(buffer.toString() + "qiqifei!@#");
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyList.clear();
        if(!TextUtils.isEmpty(MD5Sign)){
            return MD5Sign;
        }else {
            return "";
        }

    }



}
