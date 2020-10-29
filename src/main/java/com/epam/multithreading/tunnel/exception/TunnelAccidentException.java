package com.epam.multithreading.tunnel.exception;

public class TunnelAccidentException extends Exception {
    public TunnelAccidentException(String message) {
        super(message);
    }

    public TunnelAccidentException(String message, Throwable cause) {
        super(message, cause);
    }

    public TunnelAccidentException(Throwable cause) {
        super(cause);
    }
}
