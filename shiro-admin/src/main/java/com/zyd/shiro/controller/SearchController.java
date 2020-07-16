package com.zyd.shiro.controller;



import com.zyd.shiro.entity.ArcTitle;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDataService;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.service.ArcTitleService;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {



    @Autowired
    private ArcDireService arcDireService;

    @Autowired
    private ArcDataService arcDataService;

    @Autowired
    private ArcTitleService arcTitleService;

    @RequestMapping(value = "/full",method = RequestMethod.GET)
    public ResponseVO full(@RequestParam(required = false,defaultValue = "") String param, @RequestParam(required = false,defaultValue = "") String state) {
        return ResultUtil.success(null, arcDireService.selectAllVo(param,state));
    }

    @RequestMapping(value ="/dire/keys/{id}",method = RequestMethod.GET)
    public PageResult searchDireByKeys(@PathVariable(name="id") long id, @RequestParam(required = false,defaultValue = "") String param, @RequestParam(required = false,defaultValue = "1") int pageNun, @RequestParam(required = false,defaultValue = "") String state) {
        List<String> paramList = SearchUtils.paramToList(param);
        return ResultUtil.tablePage(arcDataService.selectByDireAndKey(id,paramList,state,pageNun,10));
    }
    @RequestMapping(value ="/dire/condit/{id}",method = RequestMethod.GET)
    public PageResult searchDireByCondit(@PathVariable(name="id") long id, @RequestParam(required = false,defaultValue = "") String param, @RequestParam(required = false,defaultValue = "1") int pageNun) {
        ArcTitle title =arcTitleService.findAllByFileDire(id);
        return ResultUtil.tablePage(arcDataService.selectPageByTitleId(title.getId()," 1=1 ",pageNun,10));

    }


}
