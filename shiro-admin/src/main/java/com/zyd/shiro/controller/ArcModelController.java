package com.zyd.shiro.controller;


import com.zyd.shiro.entity.ArcModel;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcModelService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/model")
public class ArcModelController {

    @Autowired
    private ArcModelService arcModelService;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO selectUserPageBySort() {
        return ResultUtil.success(null,arcModelService.findAllOrderBySort());
    }





}
