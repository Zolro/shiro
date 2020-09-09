package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDataDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcLog extends AbstractDataDO {//文件
    private String action;
    private String content;
}
