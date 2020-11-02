package com.epam.multithreading.tunnel.exception;

public class TrainCreationException extends Exception {

    public TrainCreationException(String message) {
        super(message);
    }

    public TrainCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainCreationException(Throwable cause) {
        super(cause);
    }
}
