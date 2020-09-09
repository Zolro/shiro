package com.zyd.shiro.utils;

import java.util.ArrayList;
import java.util.List;

public class SearchUtils {
    public static List<String> paramToList(String param){
        List<String> paramList = new ArrayList<>();
        String[] paramStr = param.split(" ");
        for (String s : paramStr) {
            String strim = s.trim();
            if (!strim.equals("")){
                paramList.add(strim);
            }
        }
        return paramList;
    }



}
