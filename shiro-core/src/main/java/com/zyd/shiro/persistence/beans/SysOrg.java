package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrg extends AbstractDO {
    private String name;
    private String code;

}
