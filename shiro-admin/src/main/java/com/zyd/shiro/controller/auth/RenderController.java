/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.shiro.controller.auth;

/**
 * 页面渲染相关 -- 页面跳转
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */

import com.zyd.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Controller
public class RenderController {

    @RequiresAuthentication
    @GetMapping(value = {"", "/index"})
    public String home() {
        return "index";
    }

    @RequiresPermissions("users")
    @GetMapping("/users")
    public ModelAndView user() {
        return ResultUtil.view("user/list");
    }

    @RequiresPermissions("resources")
    @GetMapping("/resources")
    public ModelAndView resources() {
        return ResultUtil.view("resources/list");
    }

    @RequiresPermissions("roles")
    @GetMapping("/roles")
    public ModelAndView roles() {
        return ResultUtil.view("role/list");
    }

    @RequiresPermissions("searchs")
    @GetMapping("/searchs")
    public ModelAndView searchs() {
        return ResultUtil.view("search/list");
    }

    @RequiresPermissions("mangers")
    @GetMapping("/mangers")
    public ModelAndView mangers(Model model, @RequestParam(required = false,defaultValue = "") String param) {
        model.addAttribute("param",param);
        return ResultUtil.view("manger/list");
    }

    @RequiresPermissions("companys")
    @GetMapping("/companys")
    public ModelAndView company(Model model, @RequestParam(required = false,defaultValue = "") String type) {
        model.addAttribute("type",type);
        return ResultUtil.view("company/list");
    }

    @RequiresPermissions("configs")
    @GetMapping("/configs")
    public ModelAndView config() {
        return ResultUtil.view("config/list");
    }


    @GetMapping("/log")
    public ModelAndView log() {
        return ResultUtil.view("log/list");
    }

    @RequiresPermissions("authoritys")
    @GetMapping("/authoritys")
    public ModelAndView authority(Model model, @RequestParam(required = false,defaultValue = "") String type) {
        model.addAttribute("type",type);
        return ResultUtil.view("authority/list");
    }

}
