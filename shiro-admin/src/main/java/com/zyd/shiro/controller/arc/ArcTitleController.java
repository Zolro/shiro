package com.zyd.shiro.controller.arc;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcTitleService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/title")
public class ArcTitleController {

    @Autowired
    private ArcTitleService titleService;

    @GetMapping(value ="/direId/{direId}")
    public ResponseVO listByDire(@PathVariable(name="direId") Long direId) {
        ArcTitle title = titleService.findByDireId(direId);
        return ResultUtil.success(null,title);
    }



}
