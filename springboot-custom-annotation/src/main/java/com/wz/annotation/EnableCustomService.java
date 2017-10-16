package com.wz.annotation;

import com.wz.config.CustomImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>自定义开关注解</p>
 *
 * @author wangzi
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(CustomImportSelector.class)
public @interface EnableCustomService {
    String value() default "simple";
}
