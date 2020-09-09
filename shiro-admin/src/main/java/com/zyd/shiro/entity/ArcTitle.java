package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcTitle extends AbstractDO {//文件标题
   private String fields;//表结构字典 以, 分割
   private Integer type;//0:文件 1:案卷
   private Long direId;
}
