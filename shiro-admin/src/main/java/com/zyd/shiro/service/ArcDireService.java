package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.vo.ArcDireConditionVo;

import java.util.List;
import java.util.Optional;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcDireService {

    ArcDire save(ArcDire arcDire);

    List<ArcDire> findAllByCode(String code);

    List<ArcDire> findAllByDireId(Long direId);

    ResponseVO delete(Long id);

    ResponseVO edit(ArcDire dire);

}
