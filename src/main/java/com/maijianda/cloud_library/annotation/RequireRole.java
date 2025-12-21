package com.maijianda.cloud_library.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRole {
    String value(); // ADMIN OR USER
}
