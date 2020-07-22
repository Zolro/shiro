package com.zyd.shiro.controller;


import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcModelService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/model")
public class ArcModelController {

    @Autowired
    private ArcModelService arcModelService;



    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO selectUserPageBySort() {
        return ResultUtil.success(null,arcModelService.findAllOrderBySort());
    }



    @GetMapping("/index")
    public ModelAndView index(Model model) {
        return ResultUtil.view("file/first");
    }
    @GetMapping("/manger")
    public ModelAndView manger(Model model, @RequestParam(required = false,defaultValue = "") String param) {
        model.addAttribute("param",param);
        return ResultUtil.view("/file/manger");
    }

}
