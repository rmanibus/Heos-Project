package fr.lavachequicode.heos.sdk.services;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XML {
    String field() default  "";
}
