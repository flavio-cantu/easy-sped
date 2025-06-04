package com.cantuaria.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Como o sistema irá permitir que o usuário edite os campos do EDF para eventuais correções.
 * Não podemos ele permitir salvar qualquer coisa, mas se a validação não deixar o sistema lento
 * iremos aplicar elas sempre que possível
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpedDatabaseValidation {

    String[] validation();

    Class<? extends DatabaseSped<?, ?>> databaseType();

    String label();

    String description();

}
