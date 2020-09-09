package com.zyd.shiro.controller.arc;



import com.zyd.shiro.entity.ArcField;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcFieldService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/field")
public class ArcFieldController {

    @Autowired
    private ArcFieldService fieldService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO list() {
        List<ArcField> fields = fieldService.findAll();
        return ResultUtil.success(null,fields);
    }

}
