package com.zyd.shiro.controller;


import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDataService;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/data")
public class ArcDataController {

    @Autowired
    private ArcDataService arcDataService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO save(ArcData arcData) {
        arcDataService.save(arcData);
        return ResultUtil.success("保存成功！");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseVO edit(ArcData arcData) {
        arcDataService.save(arcData);
        return ResultUtil.success("修改成功！");
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseVO upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        try{
            String name = file.getOriginalFilename();
            File newFile = new File(FileUtils.creDire(name));
            file.transferTo(newFile);
            return ResultUtil.success(null, FileUtils.UPLOAD_PATH_PREFIX+newFile.getName());
        }catch(Exception e){
            log.error("导入文件失败", e);
            return ResultUtil.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseVO count() {
       return ResultUtil.success(null,arcDataService.countData());
    }
    /*
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseVO count() {
        return ResultUtil.success(null,arcDataService.countByDate());
    }*/

}
