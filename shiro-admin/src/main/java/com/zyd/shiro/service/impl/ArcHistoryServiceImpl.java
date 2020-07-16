package com.zyd.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcHistory;
import com.zyd.shiro.mapper.ArcDataMapper;
import com.zyd.shiro.mapper.ArcHistoryMapper;
import com.zyd.shiro.service.ArcHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcHistoryServiceImpl implements ArcHistoryService {
    @Resource
    private ArcHistoryMapper arcHistoryMapper;


    @Resource
    private ArcDataMapper arcDataMapper;


    @Override
    public PageInfo<ArcHistory> selectAllByUser(long userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArcHistory> arcHistoryList = arcHistoryMapper.selectPageByUserId(userId);
        PageInfo<ArcHistory> pageInfo = new PageInfo(arcHistoryList);
        return pageInfo;
    }


}
