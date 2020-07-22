package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArcHistory extends AbstractDO {
    private Long user;
}
