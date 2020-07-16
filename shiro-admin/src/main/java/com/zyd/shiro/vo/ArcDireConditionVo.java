package com.zyd.shiro.vo;

import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArcDireConditionVo extends BaseConditionVO {
   private ArcDire arcDire;

}
