package com.zyd.shiro.business.annotation;

import com.zyd.shiro.business.enums.FieldTypeEnum;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValue {
    public String name();

    public FieldTypeEnum type();

}
