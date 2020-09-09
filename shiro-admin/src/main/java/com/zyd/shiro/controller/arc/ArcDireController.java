package com.zyd.shiro.controller.arc;


import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dire")
public class ArcDireController {

    @Autowired
    private ArcDireService direService;

    @GetMapping(value ="/code/{code}")
    public ResponseVO listByCode(@PathVariable(name="code") String code) {
        List<ArcDire> dires = direService.findAllByCode(code);
        return ResultUtil.success(null,dires);
    }

    @GetMapping(value ="/direId/{direId}")
    public ResponseVO listByDire(@PathVariable(name="direId") Long direId) {
        List<ArcDire> dires = direService.findAllByDireId(direId);
        return ResultUtil.success(null,dires);
    }

}
