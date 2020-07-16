package com.zyd.shiro.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.mapper.ArcDataMapper;
import com.zyd.shiro.service.ArcDataService;
import com.zyd.shiro.utils.ExcelUtils;
import com.zyd.shiro.vo.DateStatVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcDataServiceImpl implements ArcDataService {

    @Resource
    private ArcDataMapper arcDataMapper;



    @Override
    public List<ArcData> findAllByTitleId(long titleId) {
        WeekendSqls<ArcData> sqls = WeekendSqls.<ArcData>custom().andEqualTo(ArcData::getTitleId,titleId);
        return arcDataMapper.selectByExample(Example.builder(ArcData.class).where(sqls).build());
    }

    @Override
    public ArcData save(ArcData arcData) {
        arcDataMapper.insert(arcData);
        return arcDataMapper.selectByPrimaryKey(arcData.getId());
    }

    @Override
    public void getCourseListByExcel(Workbook work, long arcTitleId) throws IOException {
        List<ArcData> arcDataList = new ArrayList<>();
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);
            if(sheet == null) {
                continue;
            }
            /**
             * 创建根据以file3为唯一索引的父级目录
             */
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                ArcData arcData = ExcelUtils.creT(row, ArcData.class);
                long parDataId = 0L;
                Optional<String> file3Opt = Optional.ofNullable(arcData.getFile3());
                if(file3Opt.isPresent()){
                    WeekendSqls<ArcData> sqls = WeekendSqls.<ArcData>custom().andEqualTo(ArcData::getFile3,arcData.getFile3());
                    Optional<ArcData> recDataOpt = Optional.ofNullable(arcDataMapper.selectOneByExample(Example.builder(ArcData.class).where(sqls)));
                    if(!recDataOpt.isPresent()){
                        ArcData parData = arcData;
                        parData.setTitleId(arcTitleId);
                        parData.setFile4(null);
                        parDataId = arcDataMapper.insert(parData);
                    }else{
                        parDataId = recDataOpt.get().getId();
                    }

                }

                Optional<String> file4Opt = Optional.ofNullable(arcData.getFile4());
                if(file4Opt.isPresent()&&(!findDistinctByFile4(arcData.getFile4()).isPresent())){
                    arcData.setFile3(null);
                    arcData.setParDataId(parDataId);
                    arcData.setTitleId(arcTitleId);
                    arcDataMapper.insert(arcData);
                }
            }

        }
        work.close();
    }

    @Override
    public Optional<ArcData> findDistinctByFile4(String file4) {
        WeekendSqls<ArcData> sqls = WeekendSqls.<ArcData>custom().andEqualTo(ArcData::getFile4,file4);
        ArcData arcData = arcDataMapper.selectOneByExample(Example.builder(ArcData.class).where(sqls));
        return Optional.ofNullable(arcData);
    }

    @Override
    public PageInfo<ArcData> selectByDireAndKey(long id, List<String> params, String state,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArcData> arcDataIPage;
        if(state.equals("")||state.equals("0")){
            arcDataIPage = arcDataMapper.selectByDireAndKeyOr(id,params);
        }else{
            arcDataIPage = arcDataMapper.selectByDireAndKeyAnd(id,params);
        }
        PageInfo<ArcData> pageInfo = new PageInfo(arcDataIPage);
        return pageInfo;
    }

    @Override
    public PageInfo<ArcData> selectPageByTitleId( long titleId, String param,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArcData> arcDataList = arcDataMapper.selectPageByTitleId(titleId,param);
        PageInfo<ArcData> pageInfo = new PageInfo(arcDataList);
        return pageInfo;
    }

    @Override
    public List<ArcData> selectListByIds(String ids) {
        String[] idArr = ids.split(",");
        return arcDataMapper.selectListByIds(idArr);
    }

    @Override
    public List<DateStatVo> countByDate() {
        return arcDataMapper.countByDate();
    }


}
