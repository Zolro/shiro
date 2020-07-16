package com.zyd.shiro.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDataService;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.service.ArcTitleService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dire")
public class ArcDireController {

    @Autowired
    private ArcDireService arcDireService;

    @Autowired
    private ArcDataService arcDataService;

    @Autowired
    private ArcTitleService arcTitleService;


    @RequestMapping(value = "/condit/{id}",method = RequestMethod.GET)
    public ResponseVO selectByCondit(@PathVariable(name="id") long id) {
        return ResultUtil.success(null,arcDireService.findAllByParDire(id));
    }

    @RequestMapping(value = "/code/{code}",method = RequestMethod.GET)
    public ResponseVO selectByCondit(@PathVariable(name="code") String code) {
        return ResultUtil.success(null,arcDireService.findAllByCode(code));
    }

    @RequestMapping(value = "/title/{id}",method = RequestMethod.GET)
    public ResponseVO selectById(@PathVariable(name="id") long id) {
        return ResultUtil.success(null,arcTitleService.selectOnByDire(id));
    }

    @RequestMapping(value ="/data/{id}",method = RequestMethod.GET)
    public PageResult searchDireByCondit(@PathVariable(name="id") long id, @RequestParam(required = false,defaultValue = "1") int pageNun) {
        ArcTitle title =arcTitleService.findAllByFileDire(id);
        return ResultUtil.tablePage(arcDataService.selectPageByTitleId(title.getId(),null,pageNun,10));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO save(ArcDire arcDire) {
        arcDireService.save(arcDire);
        return ResultUtil.success("添加成功！");
    }

}
