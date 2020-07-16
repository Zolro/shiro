package com.zyd.shiro.mapper;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.plugin.BaseMapper;
import com.zyd.shiro.vo.ArcDireConditionVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArcDireMapper extends BaseMapper<ArcDire> {

    @Select("<script> SELECT * FROM `arc_dire` WHERE `id` " +
            "IN ( "+
            " SELECT DISTINCT `file_dire` FROM `arc_title` WHERE `id` IN"+
            "<foreach item='item' index='index' collection='keys' open='(' close=')' separator=','>" +
            "#{item}" +
            "</foreach>" +
            ")"+
            "</script>")
    List<ArcDire> selectAllVo(@Param("keys") List<Long> keys);



}
