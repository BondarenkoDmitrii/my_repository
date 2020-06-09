package com.bondarenko;

public class JavaException extends Exception {
    public JavaException(String message) {
        super(message);
    }

    public JavaException(String message, Throwable cause) {
        super(message, cause);
    }

    public JavaException(Throwable cause){
        super(cause);
    }
}
