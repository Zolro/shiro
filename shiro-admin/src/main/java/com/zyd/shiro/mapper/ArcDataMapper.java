package com.zyd.shiro.mapper;



import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.plugin.BaseMapper;
import com.zyd.shiro.vo.DateStatVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface ArcDataMapper extends BaseMapper<ArcData> {




    @Select("<script> SELECT DISTINCT title_id FROM `arc_data` WHERE  " +
            "<foreach item='item' index='index' collection='keys' open='(' close=')' separator='OR'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<Long> fullSearchDistinctOrTilte(@Param("keys") List<String> keys);

    @Select("<script> SELECT DISTINCT title_id FROM `arc_data` WHERE  " +
            "<foreach item='item' index='index' collection='keys' open='(' close=')' separator='AND'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<Long> fullSearchDistinctAndTilte(@Param("keys") List<String> keys);

    @Select("<script> SELECT * FROM `arc_data` " +
            " WHERE " +
            "<foreach item='item' index='index' collection='keys' open='(' close=')' separator='OR'>" +
            "search_string like concat('%',TRIM(#{item}),'%') " +
            "</foreach>" +
            " AND "+
            " title_id IN ( " +
            " SELECT id FROM arc_title WHERE file_dire = #{id} " +
            " ) " +
            "</script>")
    List<ArcData> selectByDireAndKeyOr(@Param("id") long id, @Param("keys") List<String> params);


    @Select("<script> SELECT * FROM `arc_data` " +
            " WHERE " +
            "<foreach item='item' index='index' collection='keys' open='(' close=')' separator='AND'>" +
            "search_string like concat('%',TRIM(#{item}),'%') " +
            "</foreach>" +
            " AND "+
            " title_id IN ( " +
            " SELECT id FROM arc_title WHERE file_dire = TRIM(#{id}) " +
            " ) " +
            "</script>")
    List<ArcData> selectByDireAndKeyAnd(@Param("id") long id, @Param("keys") List<String> params);

    @Select("<script> SELECT * FROM `arc_data` " +
            " WHERE " +
            " title_id = #{titleId}" +
            " AND (par_data_id=null or par_data_id=0) "+
            "<if test='param !=null'>"+
            " AND "+
            "<foreach item='item' index='index' collection='param' open='(' close=')' separator='AND'>" +
            "  search_string like concat( '%','${item}','%') "+
            "</foreach>" +
            "</if>"+
            "</script>")
    List<ArcData> selectPageByTitleId(@Param("titleId") long titleId, @Param("param") String[] param);

    @Select("<script> SELECT * FROM `arc_data` " +
            " WHERE " +
            " par_data_id = #{dataId}" +
            "<if test='param !=null'>"+
            " AND "+
            "<foreach item='item' index='index' collection='param' open='(' close=')' separator='AND'>" +
            "  search_string like concat( '%','${item}','%') "+
            "</foreach>" +
            "</if>"+
            "</script>")
    List<ArcData> selectCaseByDataId(@Param("dataId") long dataId, @Param("param") String[] param);

    @Select("<script> SELECT * FROM `arc_data` " +
            " WHERE " +
            "<foreach item='item' index='index' collection='ids' open='(' close=')' separator='OR'>" +
            "`id`=TRIM(#{item})" +
            "</foreach>" +
            "</script>")
    List<ArcData> selectListByIds(@Param("ids") String[] ids);

    @Select("<script> SELECT file5, COUNT(*) FROM `arc_data` " +
            " GROUP BY `date` " +
            "</script>")
    List<DateStatVo> countByDate();

    @Select("<script> SELECT COUNT(DISTINCT par_data_id) FROM `arc_data` " +
            "</script>")
    int countCase();

    @Select("<script> SELECT COUNT(*) FROM arc_data " +
            "WHERE par_data_id=0 " +
            "AND " +
            "id NOT IN (SELECT DISTINCT par_data_id FROM arc_data) " +
            "</script>")
    int countFile();

    Optional<ArcData> findDistinctByFile4(String file4);
    Optional<ArcData> findDistinctByFile3(String file3);
}
