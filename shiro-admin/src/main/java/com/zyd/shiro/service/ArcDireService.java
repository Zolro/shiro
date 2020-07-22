package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcDire;
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
    Optional<ArcDire> findDistinctByNameAndParDire(String name, long parDire);
    Optional<ArcDire> findDistinctByCodeAndName(String code, String name);
    ArcDire save(ArcDire arcDire);
    List<ArcDire> findAllByCode(String code);
    List<ArcDire> findAllByParDire(long parDire);
    List<ArcDire> selectAllVo(String param, String state);
    ArcDire findById(long id);

}
