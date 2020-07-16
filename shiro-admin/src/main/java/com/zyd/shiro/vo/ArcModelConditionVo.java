package com.zyd.shiro.vo;

import com.zyd.shiro.entity.ArcModel;
import com.zyd.shiro.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArcModelConditionVo extends BaseConditionVO {
    private ArcModel arcModel;
}
