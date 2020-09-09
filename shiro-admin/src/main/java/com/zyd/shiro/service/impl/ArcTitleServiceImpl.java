package com.zyd.shiro.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcField;
import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.entity.ArcLog;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.mapper.ArcFieldMapper;
import com.zyd.shiro.mapper.ArcLogMapper;
import com.zyd.shiro.mapper.ArcTitleMapper;
import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.persistence.mapper.SysUserMapper;
import com.zyd.shiro.service.ArcLogService;
import com.zyd.shiro.service.ArcTitleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcTitleServiceImpl implements ArcTitleService {

    @Resource
    private ArcTitleMapper titleMapper;
    @Resource
    private ArcFieldMapper fieldMapper;


    @Override
    public void saveOrUpd(String[] heads, Long direId,int type){
        if(heads.length==0){
            return;
        }
        String fields = "";
        for(String head:heads){
            if(null!=head&&!head.trim().equals("")){
                WeekendSqls<ArcField> sqls = WeekendSqls.<ArcField>custom().andEqualTo(ArcField::getCode,head);
                ArcField field = fieldMapper.selectOneByExample(Example.builder(ArcField.class).where(sqls).build());
                if(field!=null){
                    fields+=field.getId()+",";
                }

            }

        }
        if(fields.equals("")){
            return;
        }
        fields = fields.substring(0, fields.length() - 1);
        WeekendSqls<ArcTitle> sqls = WeekendSqls.<ArcTitle>custom().andEqualTo(ArcTitle::getDireId,direId);
        ArcTitle title = titleMapper.selectOneByExample(Example.builder(ArcTitle.class).where(sqls).build());
        if(title==null){
            title = new ArcTitle();
            title.setFields(fields);
            title.setDireId(direId);
            title.setType(type);
            title.setCreateTime(new Date());
            titleMapper.insert(title);
            return;
        }
        title.setType(type);
        title.setFields(fields);
        title.setUpdateTime(new Date());
        titleMapper.updateByPrimaryKeySelective(title);
    }

    @Override
    public ArcTitle findByDireId(long direId) {
        WeekendSqls<ArcTitle> sqls = WeekendSqls.<ArcTitle>custom().andEqualTo(ArcTitle::getDireId,direId);
        ArcTitle title = titleMapper.selectOneByExample(Example.builder(ArcTitle.class).where(sqls).build());
        return title;
    }
}
