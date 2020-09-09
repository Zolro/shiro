package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.ResponseVO;

import java.util.List;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcTitleService {

    void saveOrUpd(String[] head,Long direId,int type);

    ArcTitle findByDireId(long direId);
}
