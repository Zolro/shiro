package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.mapper.ArcDireMapper;
import com.zyd.shiro.mapper.ArcFileTypeMapper;
import com.zyd.shiro.service.ArcFileTypeService;
import com.zyd.shiro.util.ResultUtil;
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
    private ArcFileTypeMapper fileTypeMapper;

    @Resource
    private ArcDireMapper direMapper;


    @Override
    public ArcFileType save(ArcFileType arcFileType) {
        fileTypeMapper.insert(arcFileType);
         return fileTypeMapper.selectByPrimaryKey(arcFileType);
    }

    @Override
    public List<ArcFileType> findAll() {
        return fileTypeMapper.findAll();
    }


    @Override
    public ResponseVO delete(Long id) {
        ArcFileType fileType = fileTypeMapper.selectByPrimaryKey(id);
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getCode,fileType.getCode());
        int count = direMapper.selectCountByExample(Example.builder(ArcDire.class).where(sqls).build());
        if(count>0){
            return ResultUtil.error("该类型档案已有下属文档，无法删除！");
        }
        fileTypeMapper.deleteByPrimaryKey(id);
        return  ResultUtil.success("删除成功！");
    }

    @Override
    public ResponseVO edit(ArcFileType arcFileType) {
        ArcFileType fileType = fileTypeMapper.selectByPrimaryKey(arcFileType.getId());
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getCode,fileType.getCode());
        int count = direMapper.selectCountByExample(Example.builder(ArcDire.class).where(sqls).build());
        if(count>0){
            if(!arcFileType.getCode().equals(fileType.getCode())){
                return ResultUtil.error("该类型档案已有下属文档，无法修改标识！");
            }
        }
        fileTypeMapper.updateByPrimaryKeySelective(arcFileType);
        return  ResultUtil.success("修改成功！");
    }


}
