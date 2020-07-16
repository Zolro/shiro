package com.zyd.shiro.mapper;

import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArcFileTypeMapper extends BaseMapper<ArcFileType> {

    @Select("SELECT * FROM arc_file_type")
    List<ArcFileType> findAll();
}
