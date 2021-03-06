package com.thales.exceptions;

public class BadUsernameException extends Exception{
    public BadUsernameException() {}

    public BadUsernameException(String message) { super(message); }

    public BadUsernameException(String message, Throwable cause) { super(message, cause); }
}
