package com.cantuaria.util;

public class MessageException extends RuntimeException {
    public MessageException(String message) {
        super(message);
    }

    public MessageException(Exception e) {
        super(e.getMessage(), e);
    }
}
