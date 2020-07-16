package com.zyd.shiro.mapper;


import com.zyd.shiro.entity.ArcHistory;
import com.zyd.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArcHistoryMapper extends BaseMapper<ArcHistory> {

    @Select("<script> SELECT * FROM `arc_history` WHERE `id` " +
            "AND"+
            " user = #{user}"+
            "</script>")
    List<ArcHistory> selectPageByUserId(@Param("user") long userId);
}
