package com.cantuaria.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação que indica que o gerador/validador deve entrar no
 * objeto para continuar o processamento.
 * Isso evida que o relacionamento bidirecional do JPA cause loops
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpedInnerObject {
    boolean required() default false;
}
