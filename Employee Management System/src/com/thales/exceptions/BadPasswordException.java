package com.thales.exceptions;

public class BadPasswordException extends Exception{

    public BadPasswordException() {}

    public BadPasswordException(String message) { super(message); }

    public BadPasswordException(String message, Throwable cause) { super(message, cause); }
}
