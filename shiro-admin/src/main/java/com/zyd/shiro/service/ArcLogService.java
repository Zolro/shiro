package com.zyd.shiro.service;


import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcLog;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcLogService {

    PageInfo<ArcLog> findAll(Integer pageNum,Integer pageSize,String param);

    void save(ArcLog log);

    void generate(String action);


}
