package com.zyd.shiro.service.impl;



import com.zyd.shiro.entity.ArcModel;
import com.zyd.shiro.mapper.ArcModelMapper;
import com.zyd.shiro.service.ArcModelService;
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
public class ArcModelServiceImpl implements ArcModelService {

    @Resource
    private ArcModelMapper arcModelMapper;


    @Override
    public List<ArcModel> findAllOrderBySort() {
        return arcModelMapper.findAllOrderBySort();
    }
}
