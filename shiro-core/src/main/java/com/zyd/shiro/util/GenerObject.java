package com.zyd.shiro.util;

import com.zyd.shiro.business.annotation.ObjectValue;
import com.zyd.shiro.business.enums.ResourceTypeEnum;
import com.zyd.shiro.persistence.beans.SysResources;
import com.zyd.shiro.persistence.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Queue;

public class GenerObject {

    @Autowired
    private static SysResourceMapper sysResourceMapper;

    //生成权限结构表
    public static void  generatePermissions(ObjectValue objectValue, Queue<FieldObject> fieldObjects){
        String code = objectValue.code()+"s";
        SysResources sysResources =new SysResources();
        sysResources.setName(objectValue.name());
        sysResources.setType(ResourceTypeEnum.menu.getInfo());
        sysResources.setUrl("/"+code);
        sysResources.setPermission(code);
        sysResources.setSort(1);
        sysResources.setAvailable(true);
        sysResources.setCreateTime(new Date());
        sysResources.setUpdateTime(new Date());
        sysResourceMapper.insert(sysResources);
    }
    private static void generButtonComput(long parentId,String name,String code){
        SysResources sysResources =new SysResources();
        sysResources.setName(name);
        sysResources.setType(ResourceTypeEnum.button.getInfo());
        sysResources.setUrl(null);
        sysResources.setPermission(code);
        sysResources.setParentId(parentId);
        sysResources.setSort(1);
        sysResources.setAvailable(true);
        sysResources.setCreateTime(new Date());
        sysResources.setUpdateTime(new Date());
        sysResourceMapper.insert(sysResources);
    }
}
