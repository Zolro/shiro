package com.zyd.shiro.service;


import com.zyd.shiro.entity.ArcField;
import com.zyd.shiro.entity.ArcFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcFieldService {

    List<ArcField> findAll();

    Map<String,String> fieldMatch(String fields);
}
