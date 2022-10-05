package com.yj.user.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yj
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
