package com.zyd.shiro.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ArcUtils {
    @SneakyThrows
    public static <T> Map<Integer,String> getT(Class<T> tClass, int num, T t){
        Map<Integer,String> map = new HashMap<>();
        for(int i=0;i<num;i++){
            Method method = tClass.getDeclaredMethod("getFile"+i);
            String val = (String)method.invoke(t);
            if(val!=null){
                map.put(i,val);
            }
        }
        return map;
    }
    @SneakyThrows
    public static <T> String[] getTitle(Class<T> tClass, int num,T t){
        String[] titleArr = new String[num];
        for(int i=0;i<num;i++){
            Method method = tClass.getDeclaredMethod("getFile"+i);
            String val = (String)method.invoke(t);
            titleArr[i] = val;
        }
        return titleArr;
    }
}
