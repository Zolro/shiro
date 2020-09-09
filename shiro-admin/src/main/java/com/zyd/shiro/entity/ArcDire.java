package com.zyd.shiro.entity;


import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcDire extends AbstractDO {//项目
    private String code;// 档案类型
    private String name;//名称
    private Integer sort;//排序字段
    private Boolean whet;//是否是文件
    private Long direId;
}
