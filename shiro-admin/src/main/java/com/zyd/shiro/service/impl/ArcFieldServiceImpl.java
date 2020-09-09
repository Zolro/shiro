package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcField;
import com.zyd.shiro.mapper.ArcDireMapper;
import com.zyd.shiro.mapper.ArcFieldMapper;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.service.ArcFieldService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcFieldServiceImpl implements ArcFieldService {
    @Resource
    private ArcFieldMapper fieldMapper;

    @Override
    public List<ArcField> findAll() {
        return fieldMapper.selectAll();
    }


    @Override
    public Map<String, String> fieldMatch(String fields) {
        Map<String,String> fieldMap = new HashMap<>();
        if(fields.contains("|")){
            String[] filesArr = fields.split("\\|");
            for(String fileStr:filesArr){
                String[] fieldArr = fileStr.split(",");
                fieldMap.put(fieldArr[0],fieldArr[1]);
            }
        }else{
            String[] fieldArr = fields.split(",");
            fieldMap.put(fieldArr[0],fieldArr[1]);
        }

        Map<String,String> fieldMatch = new HashMap<>();
        for(String key:fieldMap.keySet()){
            ArcField field = fieldMapper.selectByPrimaryKey(Long.parseLong(key));
            if(field!=null&&!field.getName().equals(fieldMap.get(key))){
                fieldMatch.put(field.getName(),fieldMap.get(key));
            }
        }
        return fieldMatch;
    }
}
