package com.zyd.shiro.controller.arc;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.service.ArcFileTypeService;
import com.zyd.shiro.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fileType")
public class ArcFileTypeController {
    @Autowired
    private ArcFileTypeService fileTypeService;

    @Autowired
    private ArcDireService direService;



    /**
     * 新增档案类型
     *
     * @param arcFileType
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO save(ArcFileType arcFileType) {
        fileTypeService.save(arcFileType);
        return ResultUtil.success("添加成功！");
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO list() {
        List<ArcFileType> fileTypes = fileTypeService.findAll();
        return ResultUtil.success(null,fileTypes);
    }


    @RequestMapping(value ="/simple",method = RequestMethod.GET)
    public ResponseVO simpleList() {
        List<ArcFileType> fileTypes = fileTypeService.findAll();
        JSONArray all =new JSONArray();
        for(ArcFileType fileType:fileTypes){
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("id",fileType.getId());
            jsonObject.put("name",fileType.getName());
            jsonObject.put("type",fileType.getType());
            JSONArray array =new JSONArray();
            List<ArcDire> dires = direService.findAllByCode(fileType.getCode());
            for(ArcDire dire:dires){
                JSONObject object = new JSONObject();
                object.put("id",dire.getId());
                object.put("name",dire.getName());
                array.add(object);
            }
            jsonObject.put("list",array);
            all.add(jsonObject);
        }
        return ResultUtil.success(null,all);
    }


    @DeleteMapping("/{id}")
    public ResponseVO delete(@PathVariable Long id) {
        return fileTypeService.delete(id);
    }

    @PutMapping
    public ResponseVO edit(ArcFileType arcFileType) {
        return fileTypeService.edit(arcFileType);
    }



}
