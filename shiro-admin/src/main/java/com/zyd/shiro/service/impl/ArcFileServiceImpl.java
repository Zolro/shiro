package com.zyd.shiro.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.mapper.ArcFileMapper;
import com.zyd.shiro.service.ArcFileService;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.vo.FileConditionVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;


import javax.annotation.Resource;
import java.util.*;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcFileServiceImpl implements ArcFileService {
    @Resource
    private ArcFileMapper fileMapper;


    @Override
    public Optional<ArcFile> findByLevelNumber(String levelNumber) {
        WeekendSqls<ArcFile> sqls = WeekendSqls.<ArcFile>custom().andEqualTo(ArcFile::getLevelNumber,levelNumber);
        ArcFile file = fileMapper.selectOneByExample(Example.builder(ArcFile.class).where(sqls).build());
        return Optional.ofNullable(file);
    }

    @Override
    public ArcFile add(ArcFile file) {
        file.setCreateTime(new Date());
        file.setUpdateTime(new Date());
        fileMapper.insert(file);
        return file;
    }
    @Override
    public void edit(ArcFile file) {
        file.setCreateTime(new Date());
        file.setUpdateTime(new Date());
        fileMapper.updateByPrimaryKeySelective(file);
    }


    @Override
    public Map<String, Integer> countData() {
        Map<String,Integer> map =new HashMap<>();

        int eleNumber = fileMapper.countEle();
        int fileNumber = fileMapper.countFile();
        int caseNumber = fileMapper.countCase();

        map.put("fileNumber",fileNumber);
        map.put("eleNumber",eleNumber);
        map.put("caseNumber",caseNumber);
        return map;
    }

    @Override
    public Optional<ArcFile> findByFileNumber(String fileNumber) {
        WeekendSqls<ArcFile> sqls = WeekendSqls.<ArcFile>custom().andEqualTo(ArcFile::getFileNumber,fileNumber).andEqualTo(ArcFile::getType,0);
        ArcFile file = fileMapper.selectOneByExample(Example.builder(ArcFile.class).where(sqls).build());
        return Optional.ofNullable(file);
    }

    @Override
    public PageInfo<ArcFile> selectPageByDireId(Long direId,int pageNumber,int pageSize,String param) {
        PageHelper.startPage(pageNumber,pageSize);
        List<ArcFile> files = fileMapper.selectPageByDireId(direId,param);
        return new PageInfo(files);
    }

    @Override
    public long countByDireId(long direId, String param) {
        return fileMapper.countByDireId(direId,param);
    }

    @Override
    public List<ArcFile> selectPageByFileNumber(String fileNumber) {
        WeekendSqls<ArcFile> sqls = WeekendSqls.<ArcFile>custom().andEqualTo(ArcFile::getFileNumber,fileNumber).andEqualTo(ArcFile::getType,1);
        List<ArcFile> files = fileMapper.selectByExample(Example.builder(ArcFile.class).where(sqls).build());
        return files;
    }

    @Override
    public ResponseVO delete(long id) {
        ArcFile file =  fileMapper.selectByPrimaryKey(id);
        if(null==file) return ResultUtil.error("不存在主键是["+id+"]的值");

        if(file.getLevelNumber()!=null){
            fileMapper.deleteByPrimaryKey(id);
        }else{
            WeekendSqls<ArcFile> sqls = WeekendSqls.<ArcFile>custom().andEqualTo(ArcFile::getFileNumber,file.getFileNumber());
            fileMapper.deleteByExample(Example.builder(ArcFile.class).where(sqls).build());
        }
        return ResultUtil.success("删除成功！");
    }

    @Override
    public PageInfo<ArcFile> fullSearch(int page, int limit, String param) {
        PageHelper.startPage(page,limit);
        List<ArcFile> files = fileMapper.fullSearch(param);
        return new PageInfo(files);
    }


}
