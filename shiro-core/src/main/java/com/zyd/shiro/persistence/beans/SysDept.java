package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysDept extends AbstractDO {
    private String code;
    private String name;
    private Long orgId;
    
    @Transient
    private SysOrg parent;
}
