package com.wz.annotation;

import com.wz.config.CustomImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义开关注解
 * Created by wangzi on 2017-08-20.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(CustomImportSelector.class)
public @interface EnableCustomService {
    String value() default "simple";
}
