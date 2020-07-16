package com.zyd.shiro.service;


import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.vo.DateStatVo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcDataService {
    List<ArcData> findAllByTitleId(long titleId);
    ArcData save(ArcData arcData);
    void getCourseListByExcel(Workbook work, long arcTitleId) throws IOException;

    Optional<ArcData> findDistinctByFile4(String file4);

    PageInfo<ArcData> selectByDireAndKey(long id, List<String> params, String state,Integer pageNum,Integer pageSize);

    PageInfo<ArcData> selectPageByTitleId(long titleId, String param,Integer pageNum,Integer pageSize);

    List<ArcData> selectListByIds(String ids);

    List<DateStatVo> countByDate();
}
