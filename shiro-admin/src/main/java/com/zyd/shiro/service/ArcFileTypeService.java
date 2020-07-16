package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcFileType;

import java.util.List;
import java.util.Optional;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcFileTypeService {
    Optional<ArcFileType> findDistinctByCode(String code);
    ArcFileType save(ArcFileType archivesType);
    List<ArcFileType> findAll();

}
