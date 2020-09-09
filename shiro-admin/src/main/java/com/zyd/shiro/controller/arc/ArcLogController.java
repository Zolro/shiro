package com.zyd.shiro.controller.arc;


import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.service.ArcLogService;
import com.zyd.shiro.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/log")
public class ArcLogController {

    @Autowired
    private ArcLogService logService;

    @RequestMapping(value ="/condit",method = RequestMethod.GET)
    public PageResult searchCaseByCondit(@RequestParam(required = false,defaultValue = "") String param, @RequestParam(required = false,defaultValue = "1") int pageNum) {
        return ResultUtil.tablePage(logService.findAll(pageNum,10,param));
    }



}
