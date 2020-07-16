package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.mapper.ArcFileTypeMapper;
import com.zyd.shiro.service.ArcFileTypeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcFileTypeServiceImpl implements ArcFileTypeService {
    @Resource
    private ArcFileTypeMapper arcFileTypeMapper;

    @Override
    public Optional<ArcFileType> findDistinctByCode(String code) {
        WeekendSqls<ArcFileType> sqls = WeekendSqls.<ArcFileType>custom().andEqualTo(ArcFileType::getCode,code);
        ArcFileType arcFileType = arcFileTypeMapper.selectOneByExample(Example.builder(ArcFileType.class).where(sqls).build());
        return Optional.ofNullable(arcFileType);
    }

    @Override
    public ArcFileType save(ArcFileType arcFileType) {

        arcFileTypeMapper.insert(arcFileType);
         return arcFileTypeMapper.selectByPrimaryKey(arcFileType);
    }

    @Override
    public List<ArcFileType> findAll() {
        return arcFileTypeMapper.findAll();
    }


}
