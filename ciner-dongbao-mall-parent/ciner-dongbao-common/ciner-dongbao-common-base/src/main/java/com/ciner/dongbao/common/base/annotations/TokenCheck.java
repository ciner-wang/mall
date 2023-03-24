package com.ciner.dongbao.common.base.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用在哪儿，作用在方法上
@Target(ElementType.METHOD)
//保留在哪儿，
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheck {

    //是否校验token
    boolean required() default true;

}
