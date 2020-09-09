package com.zyd.shiro.entity;

import com.zyd.shiro.business.annotation.FieldValue;
import com.zyd.shiro.business.enums.FieldTypeEnum;
import com.zyd.shiro.framework.object.AbstractDO;
import com.zyd.shiro.framework.object.AbstractDataDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class ArcField {//字段字典

    @FieldValue(name="主键",type = FieldTypeEnum.VALUE)
    private Long id;

    @FieldValue(name="名称",type = FieldTypeEnum.VALUE)
    private String name;

    @FieldValue(name="代码",type = FieldTypeEnum.VALUE)
    private String code;



}
