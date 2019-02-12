package com.easyboot.config.log.annotation;

import java.lang.annotation.*;

/**
 * Created by 12093 on 2019/1/18.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogSubject {

    String value() default "";


}
