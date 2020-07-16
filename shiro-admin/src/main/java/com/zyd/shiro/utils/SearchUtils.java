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
    // select * from user where search_string like
  /*  private static String splieFullSearchSql(List<String> paramList ,String state){
        String oRand = state.equals("")||state.equals("0")?"OR":"AND";
        String sql="";
        for(int i=0;i<paramList.size();i++){
            if(i==0){
                sql+=" search_string LIKE '%"+paramList.get(i)+"%' ";
            }else{
                sql+=" "+oRand+" search_string LIKE '%"+paramList.get(i)+"%' ";
            }
        }
        return sql;
    }
    public static String creFullSearch(String param,String state){
        List<String> paramList = paramToList(param);
        String sqlsub = splieFullSearchSql(paramList,state);
        return new SQL() {{
            SELECT_DISTINCT("title_id");
            FROM("arc_data");
            WHERE("par_data_id=0");
            AND();
            WHERE(sqlsub);
        }}.toString();
    }*/



}
