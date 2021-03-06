package com.thales.exceptions;

public class WrongUsernameException extends Exception{

    public WrongUsernameException() {}

    public WrongUsernameException(String message) { super(message); }

    public WrongUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
