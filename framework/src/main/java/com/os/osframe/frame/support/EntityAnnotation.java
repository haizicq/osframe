package com.os.osframe.frame.support;

import java.lang.annotation.*;

/**
 * 对实体新增的主键，
 *  用于标记实体相关的处理，如支撑类相关的独立表等、是否进行数据权限过滤
 * Created by wangchun on 16/5/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface EntityAnnotation {

    boolean filter()  default false;//是否进行权限校验,默认是不进行权限验证
    String supportTableSuffix() default "";//支持类的分表后缀
}
