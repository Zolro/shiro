package com.zyd.shiro.controller;


import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcHistory;
import com.zyd.shiro.service.ArcHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/history")
public class ArcHistoryController {
    @Autowired
    private ArcHistoryService arcHistoryService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public PageInfo<ArcHistory> selectByCondit(@PathVariable(name="id") long id, @RequestParam(required = false,defaultValue = "1") int pageNum) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return arcHistoryService.selectAllByUser(id,pageNum,10);
    }

}
