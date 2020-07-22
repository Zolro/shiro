package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.mapper.ArcTitleMapper;
import com.zyd.shiro.service.ArcTitleService;
import com.zyd.shiro.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcTitleServiceImpl implements ArcTitleService {
    @Resource
    private ArcTitleMapper arcTitleMapper;


    @Override
    public void save(ArcTitle arcTitle) {
        arcTitleMapper.insertSelective(arcTitle);
    }

    @Override
    public ArcTitle creTitle(Workbook work, long direId) {
        ArcTitle arcTitle = findAllByFileDire(direId);
        if(arcTitle!=null){
            return arcTitle;
        }
        Sheet sheet = work.getSheetAt(0);
        Row row = sheet.getRow(0);
        arcTitle = ExcelUtils.creT(row,ArcTitle.class);
        arcTitle.setFileDire(direId);
        arcTitle.setFileNum(new Integer(row.getLastCellNum()));
        arcTitleMapper.insert(arcTitle);
        return arcTitleMapper.selectOne(arcTitle);
    }


    @Override
    public ArcTitle findAllByFileDire(long fileDire) {
        WeekendSqls<ArcTitle> sqls = WeekendSqls.<ArcTitle>custom().andEqualTo(ArcTitle::getFileDire,fileDire);
        ArcTitle arcTitle = arcTitleMapper.selectOneByExample(Example.builder(ArcTitle.class).where(sqls).build());
        return arcTitle;
    }

    @Override
    public ArcTitle selectOnByDire(long id) {
        WeekendSqls<ArcTitle> sqls = WeekendSqls.<ArcTitle>custom().andEqualTo(ArcTitle::getFileDire,id);
        return arcTitleMapper.selectOneByExample(Example.builder(ArcTitle.class).where(sqls).build());
    }

    @Override
    public ArcTitle selectOnById(long id) {

        return arcTitleMapper.selectByPrimaryKey(id);
    }


}
