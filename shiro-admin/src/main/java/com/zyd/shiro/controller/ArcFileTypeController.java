package com.zyd.shiro.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyd.shiro.entity.ArcData;
import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.entity.ArcFileType;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.service.ArcDireService;
import com.zyd.shiro.service.ArcFileTypeService;
import com.zyd.shiro.util.ResultUtil;
import com.zyd.shiro.vo.ArcDireVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/fileType")
public class ArcFileTypeController {
    @Autowired
    private ArcFileTypeService arcFileTypeService;
    @Autowired
    private ArcDireService arcDireService;


    //IDUS


    /**
     * 新增用户
     *
     * @param arcFileType
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO save(ArcFileType arcFileType) {
        arcFileTypeService.save(arcFileType);
        return ResultUtil.success("添加成功！");
    }

    //TODO
    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO list() {
        List<ArcFileType> arcFileTypes = arcFileTypeService.findAll();
        JSONArray all =new JSONArray();
        for(ArcFileType arcFileType:arcFileTypes){
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("id",arcFileType.getId());
            jsonObject.put("name",arcFileType.getName());
            jsonObject.put("type",arcFileType.getType());
            JSONArray array =new JSONArray();
            List<ArcDire> arcDires = arcDireService.findAllByCode(arcFileType.getCode());
            for(ArcDire arcDire:arcDires){
                JSONObject object = new JSONObject();
                object.put("id",arcDire.getId());
                object.put("name",arcDire.getName());
                array.add(object);
            }
            jsonObject.put("list",array);
            all.add(jsonObject);
        }
        return ResultUtil.success(null,all);
    }



}
