package com.zyd.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/folder")
    public String uploadFolder() {
        return "upload";
    }

    @RequestMapping(value = "/file")
    public String uploadFile() {
        return "dire";
    }

}
