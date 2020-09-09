package com.zyd.shiro.service;


import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcFileService {
    Optional<ArcFile> findByLevelNumber(String levelNumber);

    void add(ArcFile file);

    void edit(ArcFile file);

    Map<String,Integer> countData();

    Optional<ArcFile> findByFileNumber(String fileNumber);

    PageInfo<ArcFile> selectPageByDireId(Long direId,int pageNumber,int pageSize,String param);

    long countByDireId(long direId, String param);

    List<ArcFile> selectPageByFileNumber(String fileNumber);

    void delete(long id);
}
