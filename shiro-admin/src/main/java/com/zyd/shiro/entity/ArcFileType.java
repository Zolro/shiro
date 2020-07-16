package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcFileType extends AbstractDO {//档案类型
    private String name;
    private String code;//唯一识别码d
    private int boxMode;//盒模式 1是 0否
    private String boxName;//盒名称
    private int finisMode;//整理模式 1自动 0手动
    private String source;//档案来源
    private String remarks;//备注
    private int sort;
}
