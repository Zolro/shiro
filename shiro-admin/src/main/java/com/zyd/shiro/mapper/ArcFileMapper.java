package com.zyd.shiro.mapper;


import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ArcFileMapper extends BaseMapper<ArcFile> {

    @Select("<script> SELECT COUNT(*) FROM arc_file" +
            " WHERE volume_number IS NULL "+
            " OR " +
            " volume_number=0 " +
            "</script>")
    int countFile();

    @Select("<script> SELECT COUNT(*) FROM arc_file" +
            " WHERE volume_number IS NOT NULL " +
            " AND " +
            " volume_number !=0 " +
            "</script>")
    int countCase();

    @Select("<script> SELECT COUNT(*) FROM arc_file " +
            " WHERE including=1 " +
            "</script>")
    int countEle();


    @Select("<script> SELECT * FROM `arc_file` " +
            " WHERE " +
            " dire_id = #{direId}" +
            " AND type = 0"+
            "<if test='param !=null'>"+
            " AND "+
            " ( " +
            " file_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " category_name LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " file_category LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " fond_name LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_period_code LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " secret_level LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " file_title LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " organization LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " remarks LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_location LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " license_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " carriers_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " fond_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " files_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " box_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " person LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " annex LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " page_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " problem LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_time LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " subject_heading LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " note LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " year LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " part_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " volume_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " category_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " writing_date LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " pages_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " catalog_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%')  " +
            " ) "+
            "</if>"+
            "</script>")
    List<ArcFile> selectPageByDireId(@Param("direId") long direId,@Param("param") String param);


    @Select("<script> SELECT COUNT(*) FROM `arc_file` " +
            " WHERE " +
            " dire_id = #{direId}" +
            " AND type = 0"+
            "<if test='param !=null'>"+
            " AND "+
            " ( " +
            " file_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " category_name LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " file_category LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " fond_name LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_period_code LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " secret_level LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " file_title LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " organization LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " remarks LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_location LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " license_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " carriers_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " fond_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " files_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " box_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " person LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " annex LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " page_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " problem LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " storage_time LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " subject_heading LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " note LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " year LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " part_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " volume_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " category_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " writing_date LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " pages_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%') or " +
            " catalog_number LIKE CONCAT('%',#{param , jdbcType=VARCHAR},'%')  " +
            " ) "+
            "</if>"+
            "</script>")
    long countByDireId(@Param("direId") long direId,@Param("param") String param);



    @Select("<script> SELECT * FROM arc_file" +
            " WHERE ${param} "+
            " AND " +
            " `type`=0 " +
            "</script>")
    List<ArcFile> fullSearch(@Param("param") String param);
}
