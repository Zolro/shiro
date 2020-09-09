package com.zyd.shiro.util;

import com.zyd.shiro.business.enums.FieldTypeEnum;
import lombok.Data;

@Data
public class FieldObject {
    private String name;
    private String field;
    private FieldTypeEnum fieldTypeEnum;

}
