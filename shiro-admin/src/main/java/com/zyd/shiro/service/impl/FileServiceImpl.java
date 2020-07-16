package com.zyd.shiro.service.impl;


import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.mapper.ArcDataMapper;
import com.zyd.shiro.service.FileService;
import com.zyd.shiro.utils.ZipFilesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private ArcDataMapper arcDataMapper;
    @Value("${fileProps.filePath}")
    private String filePath;

    @Override
    public void downloadMulti(String ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idsArr = ids.split(",");
        List<ArcData> arcDataList = arcDataMapper.selectListByIds(idsArr);
        List<File> files = new ArrayList<>();
        for (ArcData arcData : arcDataList) {
            File file = new File(filePath + "/" + arcData.getFilePath());
            if (file.exists()) {
                files.add(file);
            } else {
                throw new Exception("文件 :" + file.getName() + "不存在,请联系管理员!");
            }
        }
        if (files.isEmpty()) {
            throw new Exception("当前选择文件不存在,请联系管理员!");
        } else {
            String tempName = "temp.zip";
            String path = filePath + "/" + tempName;
            //压缩
            ZipFilesUtil.createZipFiles(files, path, response);
            //下载
            ZipFilesUtil.downloadFile(new File(path), tempName, request, response);
        }

    }
}
