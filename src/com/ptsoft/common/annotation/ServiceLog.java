package com.ptsoft.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Service，用该方法标记的Service方法，将被拦截记录日志
 * 
 * @author fuyiyong
 */

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog
{
	String description() default "";
}
