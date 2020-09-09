package com.zyd.shiro.vo;

import com.zyd.shiro.entity.ArcFile;
import com.zyd.shiro.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileConditionVO extends BaseConditionVO {
   private ArcFile file;

}
