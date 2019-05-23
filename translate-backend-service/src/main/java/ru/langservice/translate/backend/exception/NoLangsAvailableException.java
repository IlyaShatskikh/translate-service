package ru.langservice.translate.backend.exception;

public class NoLangsAvailableException extends RuntimeException {
    public NoLangsAvailableException() {
    }
    public NoLangsAvailableException(String message) {
        super(message);
    }
    public NoLangsAvailableException(Throwable cause) {
        super(cause);
    }
}
