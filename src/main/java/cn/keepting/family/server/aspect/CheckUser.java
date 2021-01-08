package cn.keepting.family.server.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.aspect
 * @date:2021/1/7
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUser {
}
