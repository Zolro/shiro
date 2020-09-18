package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.mapper.ArcDireMapper;
import com.zyd.shiro.mapper.ArcFileMapper;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcDireServiceImpl implements ArcDireService {
    @Resource
    private ArcDireMapper direMapper;

    @Resource
    private ArcFileMapper fileMapper;


    @Override
    public ArcDire save(ArcDire arcDire) {
        direMapper.insertSelective(arcDire);
        return direMapper.selectByPrimaryKey(arcDire);
    }

    @Override
    public List<ArcDire> findAllByCode(String code) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getCode,code);
        List<ArcDire> dires = direMapper.selectByExample(Example.builder(ArcDire.class).where(sqls).orderBy("sort").build());
        return dires;
    }

    @Override
    public List<ArcDire> findAllByDireId(Long direId) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getDireId,direId);
        List<ArcDire> dires = direMapper.selectByExample(Example.builder(ArcDire.class).where(sqls).build());
        return dires;
    }


    @Override
    public ResponseVO delete(Long id) {
        WeekendSqls<ArcFile> sqls = WeekendSqls.<ArcFile>custom().andEqualTo(ArcFile::getDireId,id);
        int count = fileMapper.selectCountByExample(Example.builder(ArcFile.class).where(sqls).build());
        if(count>0){
            return ResultUtil.error("该目录下已有相关文档，无法删除！");
        }
        direMapper.deleteByPrimaryKey(id);
        return  ResultUtil.success("删除成功！");
    }

    @Override
    public ResponseVO edit(ArcDire dire) {
        direMapper.updateByPrimaryKeySelective(dire);
        return  ResultUtil.success("修改成功！");
    }


}
