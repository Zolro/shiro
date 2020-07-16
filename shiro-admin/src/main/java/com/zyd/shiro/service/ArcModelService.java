package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcModel;

import java.util.List;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcModelService {

    List<ArcModel> findAllOrderBySort();

}
