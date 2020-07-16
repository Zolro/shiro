package com.zyd.shiro.mapper;

import com.zyd.shiro.entity.ArcModel;
import com.zyd.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArcModelMapper extends BaseMapper<ArcModel> {

    @Select("SELECT * FROM arc_model order by sort")
    List<ArcModel> findAllOrderBySort();
}
