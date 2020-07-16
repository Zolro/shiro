package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcModel extends AbstractDO {
    private String name;
    private String link;
    private int sort;
}
