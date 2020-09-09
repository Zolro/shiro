package com.zyd.shiro.business.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectValue {

    public String code();

    public String name();

    public boolean add() default false;

    public boolean delete() default false;

    public boolean edit() default false;

    public boolean batchDelete() default false;

    public String customize() default "";
}
