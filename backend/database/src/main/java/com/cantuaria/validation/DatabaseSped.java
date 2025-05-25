package com.cantuaria.validation;

public interface DatabaseSped<T, ID> {

    T findBySpedCode(ID id);

}
