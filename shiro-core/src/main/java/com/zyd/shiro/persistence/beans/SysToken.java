package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysToken extends AbstractDO {
    private String token;
    private Long userId;

}
