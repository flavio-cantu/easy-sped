package com.cantuaria.validation;

/**
 * T é a entidade
 * ID é o tipo do campo de identificação do código SPED
 * @param <T>
 * @param <ID>
 */
public interface DatabaseSped<T, ID> {

    T findBySpedCode(ID id);

}
