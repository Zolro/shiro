package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.mapper.ArcDataMapper;
import com.zyd.shiro.mapper.ArcDireMapper;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.utils.SearchUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcDireServiceImpl implements ArcDireService {
    @Resource
    private ArcDireMapper arcDireMapper;

    @Resource
    private ArcDataMapper arcDataMapper;


    @Override
    public Optional<ArcDire> findDistinctByNameAndParDire(String name, long parDire) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getName,name).andEqualTo(ArcDire::getParDire,parDire);
        ArcDire arcDire = arcDireMapper.selectOneByExample(Example.builder(ArcDire.class).where(sqls).build());
        return Optional.ofNullable(arcDire);
    }

    @Override
    public Optional<ArcDire> findDistinctByCodeAndName(String code, String name) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getName,name).andEqualTo(ArcDire::getCode,code);
        ArcDire arcDire = arcDireMapper.selectOneByExample(Example.builder(ArcDire.class).where(sqls).build());
        return Optional.ofNullable(arcDire);
    }

    @Override
    public ArcDire save(ArcDire arcDire) {
        arcDireMapper.insertSelective(arcDire);
        return arcDireMapper.selectByPrimaryKey(arcDire);
    }

    @Override
    public List<ArcDire> findAllByCode(String code) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getCode,code);
        List<ArcDire> arcDires = arcDireMapper.selectByExample(Example.builder(ArcDire.class).where(sqls).build());
        return arcDires;
    }

    @Override
    public List<ArcDire> findAllByParDire(long parDire) {
        WeekendSqls<ArcDire> sqls = WeekendSqls.<ArcDire>custom().andEqualTo(ArcDire::getParDire,parDire);
        List<ArcDire> arcDires = arcDireMapper.selectByExample(Example.builder(ArcDire.class).where(sqls).build());
        return arcDires;
    }

    @Override
    public List<ArcDire> selectAllVo(String param, String state) {
        List<ArcDire> arcDires = new ArrayList<>();
        List<String> params = SearchUtils.paramToList(param);
        List<Long> titles;
        if(state.equals("")||state.equals("0")){
            titles = arcDataMapper.fullSearchDistinctOrTilte(params);
        }else{
            titles = arcDataMapper.fullSearchDistinctAndTilte(params);
        }
       if(titles.size()==0){
           return arcDires;
       }
        arcDires = arcDireMapper.selectAllVo(titles);
        return arcDires;
    }

    @Override
    public ArcDire findById(long id) {
        return arcDireMapper.selectByPrimaryKey(id);
    }


}
