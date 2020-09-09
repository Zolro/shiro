package com.zyd.shiro.mapper;


import com.zyd.shiro.entity.ArcLog;
import com.zyd.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArcLogMapper extends BaseMapper<ArcLog> {


    @Select("<script> SELECT * FROM `arc_log` " +
            " WHERE 1 = 1" +
            "<if test=\"param != null and param != '' \">" +
            "AND"+
            "`search` LIKE CONCAT('%',#{param},'%')" +
            "</if>" +
            " ORDER BY create_time DESC" +
            "</script>")
    List<ArcLog> findPageBreakByCondition(@Param("param") String param);

}
