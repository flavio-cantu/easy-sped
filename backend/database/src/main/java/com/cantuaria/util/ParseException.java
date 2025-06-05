package com.cantuaria.util;

public class ParseException extends RuntimeException {
    public ParseException(Exception e) {
        super(e.getMessage(), e);
    }
}
