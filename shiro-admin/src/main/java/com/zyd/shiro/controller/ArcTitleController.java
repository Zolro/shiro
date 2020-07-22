package com.zyd.shiro.controller;


import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcTitleService;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.utils.ArcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/title")
public class ArcTitleController {
    @Autowired
    private ArcTitleService arcTitleService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseVO get(@PathVariable(name="id") long id) {
        ArcTitle arcTitle = arcTitleService.selectOnById(id);
        String[] titleArr = ArcUtils.getTitle(ArcTitle.class,arcTitle.getFileNum(),arcTitle);
        return ResultUtil.success(null,titleArr);

    }


}
