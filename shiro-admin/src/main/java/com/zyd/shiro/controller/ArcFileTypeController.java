package com.zyd.shiro.controller;



import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcFileTypeService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/fileType")
public class ArcFileTypeController {
    @Autowired
    private ArcFileTypeService arcFileTypeService;


    //IDUS


    /**
     * 新增用户
     *
     * @param arcFileType
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO save(ArcFileType arcFileType) {
        arcFileTypeService.save(arcFileType);
        return ResultUtil.success("添加成功！");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO list() {
        return ResultUtil.success(null,arcFileTypeService.findAll());
    }
    



}
