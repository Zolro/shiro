package com.zyd.shiro.service;

import com.alibaba.fastjson.JSONObject;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;

import java.util.List;
import java.util.Optional;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcFileTypeService {


    ArcFileType save(ArcFileType fileType);

    List<ArcFileType> findAll();

    ResponseVO delete(Long id);

    ResponseVO edit(ArcFileType fileType);



}
