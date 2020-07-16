package com.zyd.shiro.service;


import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcHistory;

import java.util.List;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcHistoryService {

    PageInfo<ArcHistory> selectAllByUser(long userId,Integer pageNum,Integer pageSize);

}
